package edu.ysu.itrace;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.swt.custom.StyledText;

public class AstManager {
    public enum SCEType {
        TYPE,
        METHOD,
        VARIABLE,
        COMMENT,
    }

    public class SourceCodeEntity {
        public SCEType type;
        public String name;
        public int totalLength;
        public int startLine, endLine;
        public int startCol, endCol;
    }

    public class SCEQueryResponse {
        public SourceCodeEntity sce;
        public String fullyQualifiedName;
    }

    private StyledText styledText;
    private LinkedList<SourceCodeEntity> sourceCodeEntities =
            new LinkedList<SourceCodeEntity>();

    public AstManager(StyledText styledText) {
        this.styledText = styledText;
        reload();
    }

    public SCEQueryResponse getSCE(int lineNumber, int colNumber) {
        SourceCodeEntity responseSce = null;
        Stack<SourceCodeEntity> sceFullyQualified =
                new Stack<SourceCodeEntity>();

        for (SourceCodeEntity sce : sourceCodeEntities) {
            boolean found = true;
            if (lineNumber < sce.startLine || lineNumber > sce.endLine)
                found = false;
            if (lineNumber == sce.startLine && colNumber < sce.startCol)
                found = false;
            if (lineNumber == sce.endLine && colNumber > sce.endCol)
                found = false;
            if (found) {
                sceFullyQualified.push(sce);
                //The first encountered SCE is the most specific SCE and will be
                //returned in the response.
                if (responseSce == null)
                    responseSce = sce;
            }
        }

        if (responseSce != null) {
            String fqName = "";
            while (!sceFullyQualified.empty())
                fqName += sceFullyQualified.pop().name + ".";
            fqName = fqName.substring(0, fqName.length() - 1);

            SCEQueryResponse result = new SCEQueryResponse();
            result.sce = responseSce;
            result.fullyQualifiedName = fqName;
            return result;
        } else {
            return null;
        }
    }

    public void reload() {
        ASTParser parser = ASTParser.newParser(AST.JLS4);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(styledText.getText().toCharArray());
        parser.setResolveBindings(true);
        final CompilationUnit compileUnit =
                (CompilationUnit) parser.createAST(null);

        ASTVisitor visitor = new ASTVisitor() {
            public boolean visit(TypeDeclaration node) {
                SourceCodeEntity sce = new SourceCodeEntity();
                sce.type = SCEType.TYPE;
                sce.name = node.getName().getFullyQualifiedName();
                determineSCEPosition(compileUnit, node, sce);
                sourceCodeEntities.add(sce);
                return true;
            }

            public boolean visit(MethodDeclaration node) {
                SourceCodeEntity sce = new SourceCodeEntity();
                sce.type = SCEType.METHOD;
                sce.name = node.getName().getFullyQualifiedName();
                determineSCEPosition(compileUnit, node, sce);
                sourceCodeEntities.add(sce);
                return true;
            }
        };
        compileUnit.accept(visitor);
        //Smaller entities take higher priority. If a method appears in a class,
        //for example, a query for the method should return the method instead
        //of the class.
        Collections.sort(sourceCodeEntities,
            new Comparator<SourceCodeEntity>() {
                @Override
                public int compare(SourceCodeEntity lhs, SourceCodeEntity rhs) {
                    return lhs.totalLength - rhs.totalLength;
                }
            });
    }

    private static void determineSCEPosition(CompilationUnit compileUnit,
            ASTNode node,SourceCodeEntity sce) {
        sce.totalLength = node.getLength();
        sce.startLine = compileUnit.getLineNumber(node.getStartPosition());
        sce.endLine = compileUnit.getLineNumber(node.getStartPosition() +
                                                node.getLength());
        sce.startCol = compileUnit.getColumnNumber(node.getStartPosition());
        sce.endCol = compileUnit.getColumnNumber(node.getStartPosition() +
                                                 node.getLength());
    }
}