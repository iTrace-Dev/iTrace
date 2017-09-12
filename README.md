# iTrace

iTrace is a suite of tools for tracking eye movements with a focus on software engineering research.  The iTrace Core gathers data from an eye tracker and sends screen coordinates to all connected plugins.  Each plugin determines if the received coordinates are visible within its application and can record data about areas of interest that are being focused on.


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


## Contributing

### Branching

iTrace utilizes a `master` branch for the current tested and stable release, and a `develop` branch for work towards the next release.  New features and bugs should have an issue logged and be developed in a branch named `issue##-keyword`, where `##` represents the issue number and a `keyword` or short phrase is provided to easily identify the branch.

### Pull requests

After a feature or bug fix is completed, it should be tested and have a pull request opened for an additional person to test it before it is approved to be merged into the `develop` branch.
