Slack Tasks
---

Work in progress.  Not production ready.  Not taking contributions.

Goals:
---

Manage arbitrary tasks from Slack. The model will be new tasks are created externally from email.  Users will be registered to a given channel, and the tasks will be assigned based on who's turn it is(to be defined) or by manually claiming it.

Goal Features:
From slack you will be able to:
configure a channel to receive tasks.
register to given channel
view current task board for given period.
create task from slack (not sure this will be allowed).

from app you will be able to:
configure email parsings and assignment algorithms.

Technologies:
Currently uses openshift, but it should be easily doable to switch to heroku or cloudfoundry.
MongoDB (because I wanted to learn it)
Spring (I needed to practice more spring, particularly annotation driven)



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
