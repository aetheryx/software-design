# Conventions
## Comments:
in short: use https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
Intellij Idea uses javadoc by default to make documentation, because everyone in the team uses Intellij,
we should make use of this easy option, especially because its also recommended by us from the book:
"a philosophy of software design" from the course. This book is also a reference on writing good comments.

in short:
 - Dont repeat your code (check the philosophy book)
 - Dont talk about implementation in your interface comment
 - when making a class, ALWAYS provide an interface comment
 - make implementation comments when the implementation is not obvious (when a groupmate sais its not obvious
its not obvious)
 - supply class member comments when necessary

## Names
### variable names:
camelcase https://en.wikipedia.org/wiki/Camel_case. This is what we decided upon in one of our meetings, and
Joachim threatened to eat your laptop if you did not use camelcase for variable name. Here is why:
underscores (_) are way more annoying to write compared to capital letters, aside from the fact that camelcase
is the industry standard for variables, and taught to us in the computer programming course.

For the actual name content follow chapter 14 from the book "a philosophy of software design" for a detailed description
or follow the shorthand version which is: no names that could mean multiple different things, names should be accurate,
and use consistent naming (e.g. if you have a repository url and name it repoURL in one function, the other function
should also call it repoURL, and not URL).

### class names:
Use CapitalLetters for start and following words, LikeThisClass. For actual content naming, check the paragraph above
and the same book.

### method names:
camelCase, check https://en.wikipedia.org/wiki/Camel_case for more info.

## Commits:
try to make one commit per topic, as to not confuse yourself or others when searching through these commits.
use your personal branch for new feature development, and collaborations should be made on separate feature-specific
branches. NEVER commit or pull to main without discussion. Extra branches like developement branches may be made for
specific assignments.

