# Conventions
## Comments
in short: use https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
IntelliJ IDEA uses Javadoc by default to make documentation, because everyone in the team uses IntelliJ,
we should make use of this easy option, especially because its also recommended by us from the book:
"A Philosophy of Software Design" from the course. This book is also a reference on writing good comments.

In short:
 - Don't repeat your code (check the philosophy book)
 - Don't talk about implementation in your interface comment
 - When making a class, ALWAYS provide an interface comment
 - Make implementation comments when the implementation is not obvious (if a groupmate says it's not obvious,
then it's not obvious)
 - Supply class member comments when necessary

## Names
### Variable names
camelcase https://en.wikipedia.org/wiki/Camel_case. This is what we decided upon in one of our meetings, and
Joachim threatened to eat your laptop if you did not use camelcase for variable names. Here is why:
underscores (_) are way more annoying to write compared to capital letters, aside from the fact that camelcase
is the industry standard for variables in Java, and taught to us in the computer programming course.

For the actual name content follow chapter 14 from the book "A Philosophy of Software Design" for a detailed description,
or follow the shorthand version which is:
- avoid names that could mean multiple different things
- names should be accurate
- names should be consistent (e.g. if you have a repository url and name it repoURL in one function, the other function
should also call it repoURL, and not URL)

### Class names
Use PascalCase for start and following words, LikeThisClass. For actual content naming, check the paragraph above
and the same book.

### Method names
camelCase, check https://en.wikipedia.org/wiki/Camel_case for more info.

## Git Workflow
### Commits
Try to make one commit per topic, as to not confuse yourself or others when searching through these commits.

### Branches
#### Personal branches
Every team member has a personal branch that they can commit to at all times. The features you're working on right now are to be commited to your own personal branch.

#### Feature branches
If you are working on a feature with another team member, use a feature branch to collaborate, e.g. `feature/most-active-contributor-statistic`. Both you and the team member can merge your personal branches into this feature branch, and then collaborate on the feature branch, optionally committing to it directly.

#### Assignment branches
Whenever some scope of work has been completed, create a pull request from your personal branch (or your feature branch) to an assignment branch and request a review from all team members. In order to merge a pull request into an assignment branch, you need **at least one** review from a fellow team member, but for large features involving core aspects of the application, it is recommended to have more reviews.
Never commit to an assignment branch directly.

#### The main branch
Whenever an assignment branch is finalised, we create a pull request from the assignment branch to the main branch. We do not merge this pull request ourselves; we request a review from Linus, who will merge it for us.
Never commit to the main branch directly.


