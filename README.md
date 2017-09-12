# iTrace

iTrace is a suite of tools for tracking eye movements with a focus on software engineering research.  The iTrace Engine gathers data from an eye tracker and sends screen coordinates to all connected plugins.  Each plugin determines if the received coordinates are visible within its application and can record data about areas of interest that are being focused on.


## About this repository

This repository contains Git submodules pointing to the current version of each official plugin.


### Cloning

To automatically checkout all subomdules when cloning, add the `--recursive` argument.


### Adding submodules

`git submodule add ../iTrace-PluginName.git`, replacing iTrace-PluginName with the name of the repository in this GitHub organization.  If the repository is in a different organization, use `../../OrgName/RepoName.git`.


### Updating submodule pointers

1. Change to the submodule's directory
2. Pull the submodule
3. Fast-forward the submodule to the desired commit
4. Change to this repository's directory
5. Commit the change
