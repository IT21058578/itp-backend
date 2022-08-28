# ITP Project Codebase
Note that Git does not care about empty directories so I could not include the sub-directories for each person in this repository. Instead take it upon yourself to follow this format when implementing functions.

## For Frontend
src > *your_folder_name*
  
## For Backend
src > main > java > com > web > backend > *your_folder_name*
  
## Folder Names
- Employee Management -  - empManage
- User Management - usrManage
- Crew Assigning - crewAssign
- Service Booking - srvcBook
- Service Creation - srvcCreate
- Inventory Management - invManage
- User Profile Management - usrProfile
- Scheduling Management - schedManage
- Payment Management -paymentManage

# Inital Message
Hey guys so I set up 2 seperate git repositories for out backend. I feel like this will make deployment much easier
so I opted for this option. Depending on what you guys use you might have to figure out how to pull, push and keep your local files in sync with these remote repositories.
https://github.com/IT21058578/itp-frontend
https://github.com/IT21058578/itp-backend

In the case you plan on using VSCode you can use this handy tutorial I found to keep both folders open at the same time to work with them. https://www.youtube.com/watch?v=2yOQUtP_GcY

Here are some commands you may find helpful when working with git;
- git init -> Create a git repository in current directory
- git add * -> Add all files in current directory to the git repository
- git remote add <name> <url> -> Add a remote repository
- git commit -m "<Message>" -> Commit changes to repository
- git push <name> -> Push changes to remote repository

You may found it more intuitive to use your editors built in git tools if you wish. VSCode by default commits to "master" branch instead of "main" branch. Run this command to fix that; 
> git config --global init.defaultBranch main 
  
(Warning: This is a global configuration setting, If you want to do this locally, find a different way)

For running the frontend you may just do 
> npm start
  
Once you are in the directory. It will open on localhost:3000. The only thing that should be there is the text "Hello ITP!". Note that we are using FlowBite and TailwindCSS for our CSS Styling in the frontend.

There is a Java Extension Pack aswell as a Spring Extension Pack for VSCode to make life easier when dealing with this. You should be able to run the main application without issues using these extensions. This makes it run on localhost:8080. You should not receive anything here except a "WhiteLabel Error Page"
