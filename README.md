Slack Tasks
---

Work in progress.  Not production ready.  Not taking contributions.

Goals:
---

Manage arbitrary tasks from Slack. The model will be new tasks are created externally from email.  Users will be registered to a given channel, and the tasks will be assigned based on who's turn it is(to be defined) or by manually claiming it.

####Goal Features:
From slack you will be able to:
configure a channel to receive tasks.
register to given channel
view current task board for given period.
create task from slack (not sure this will be allowed).

from app you will be able to:
configure email parsings and assignment algorithms.

####Technologies:
Currently uses openshift, but it should be easily doable to switch to heroku or cloudfoundry.
MongoDB (because I wanted to learn it)
Spring (I needed to practice more spring, particularly annotation driven)

####Slack Command line:
assuming you created a slash command named ups.  Instructions for that will be provided later.

*/ups addtask "taskId" "taskTitle" "taskDescription"
*/ups assign taskId userName //Assign task taskId to user 
*/ups board //Shows you current assignments to all users to you personally
*/ups configurechannel //Sets channel to receive and distribute tasks
*/ups help [command] //Provides usage for commands
*/ups register userName //Registers user receive tasks in current channel
*/ups showboard //Shows current assignments to all users to the channel
*/ups unassigned //Shows unassigned tasks for current channel


Openshift CheatSheet
---
rhc tail -a slacktasks

rhc showapp slacktasks

git add --all .

git commit -a -m 'first put of all code'

git push


OpenShift
--
The OpenShift `jbossews` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat
