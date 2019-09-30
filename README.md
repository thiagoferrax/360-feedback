# 360-feedback

## Requirements overview

A project team wants 360 feedback. Team members belong to the project, but one member can belong to more than one project. The project is executed in a department of one of the company's units. All members need to use this system to give feedback to their teams and view the result of the feedback they have received.

A member can give more than one feedback to his team and see the progress of those feedbacks, as well as access the progress of the feedback the team has given him.

A non-manager member will only be able to see feedbacks about themselves, but can see how their rating is relative to the team and the company against a specified rating criteria.

Feedback is created by a team member who defines the items that need to be evaluated, as well as a rating scale for these evaluations. A feedback assessment is associated with a team and it will not be possible to perform the same assessment for more than one team at a time. Each feedback item has a name, a description and will be rated according to the rating scale. Items may have sub-items and their rating affects their parents' rating.

Each project has a manager and this manager will be able to view all feedback results from his team members, including his own. This manager may also have a manager above him and the latter manager will have access to all feedback from the teams under his supervision.

All members will be able to view the consolidated results of all projects in their department and company unit and the consolidated results for the entire company. The member can even see how they are doing with all members of their department and unit.

The system must provide real-time information so that team members can see their feedback while the team is conducting the evaluations.

Finally, the system must control user access and authorization.

## Architecture overview

#### Class diagram

![class-diagram](https://github.com/thiagoferrax/360-feedback/requirements/classDiagram.png)