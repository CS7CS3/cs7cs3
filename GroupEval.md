CS7CS3 Advanced Software Engineering Group Project

---
1. Whole Team

How was this applied in your team?

Benefits

Drawbacks

Lessons learned

---
2. Planning Game

“Planning is continuous and progressive. Every two weeks, for the next two weeks, developers estimate the cost of candidate features, and customers select those features to be implemented based upon cost and business value”

How was this applied in your team?

Benefits

Drawbacks

Lessons learned

---
3. Customer Tests

“As part of selecting each desired feature, the customers define automated acceptance tests to show that this feature is working”

How was this applied in your team?

Benefits

Drawbacks

Lessons learned

---
4. Simple Design

“The team keeps the design exactly suited for the current functionality of the system. It passes all the tests, contains no duplication, expresses everything the authors want expressed, and contains as little code as possible”.

How was this applied in your team?
We implemented our design in a simple and direct way - Every module is designed as independently as possible. For example, all HTTP requests and responses are made in separate classes, independent of entity classes. Therefore, we can easily change the HTTP request and response classes without affecting the other classes, and if entity classes are changed, we don't have to change requests/responses classes. In addittion, we enabled the compiler to make sure all the classes/methods/members are used - there's no duplicated code. 

Benefits
1. The simple design allows developers to focus on the core functionality of the system, doing all we need now. It can provide us add unnecessary code for "just in case" reasons.

2. Developers can save times on solving problems that don't exist or adding features that we don't need. In XP, it is called "YAGNI"(You Aren't Going to Need It).

3. Simple design is friendly to code refactoring because we keep the code as simple as possible. Thus, partial refactoring of the code has less impact than refactoring the code in a monolithic design.


Drawbacks
1. It is hard for new XP developers to get used to simple design since, in the past decades, developers are taught to do complex and well-defined designs. However, in XP, design is a continuous process, we are not expected to finish the design in one go, and designs are usually changed.

2. Simple design is not a silver bullet, it is a good starting point, but it is not the best starting point. When a system becomes complex, we must pay much more effort to make improve the design. 


Lessons learned
YAGNI is a good idea in development, as we are always confident to our code, we are always going to over-design, i.e. we write some code that we think we will need in the future. However, the fact is that we will never use the code, and the code makes the sytem more complex.

---
5. Pair Programming

“All production software is built by two programmers, sitting side by side, at the same machine”.

How was this applied in your team?

Benefits

Drawbacks

Lessons learned

---
6. Test-Driven Development

How was this applied in your team?

For test driven development, our team defined multiple apis in python accroding to the possible scenarios. The test cases send HTTP requests to the server, and check if the response is exepcted. If we need a new feature, we add the test case first.

Firstly, the test cases will be failed. Then we're going to write the code. Since we knows the test well know, we can write the code in the right way. However, sometimes there might be some bugs in the code because the test cases do not cover all the edge situations. We can add the edge cases in the testsuite, run the test, and start to fix the bug.

Benefits

1. Test driven development is a great way to let developers consider the design before they start coding, as most of bugs come from ambiguous design decisions.

2. Developers can focus on the codes that are really nessesary. In traditional development, the developers may overdesign the codes, and the bugs are hard to find. In test driven development, we can make our code be clean and readable.

3. TDD is good for modulized development. Because developers can only consider one mircro feature at a time, and the test cases are written in a way that the developer can understand.

4. TDD is friendly to refactoring, developers don't worry about breaking changes. As a developer, we are imporving our skills every day. If we open the project we wrote before, most likely, you are highly possible to write a better version of the code. However, changes are dangerous. With test driven development, we can easily refactor the code without fear of breaking the whole system.

5. During test driven development, we can netualy get a high test coverage.

Drawbacks
1. Test driven development is not a silver bullet. It can help you find bugs, but if the test code itself is wrong, we cannot know it. If programmers don't understand the matter, the tests they write probably do not help.

2. Test driven development is not a way to faster the development process. We need multiple steps before we finally start writing the code.

3. As requirements are changing, the test cases are also changing. TDD requires developers maintain the cases also, changing cases can also make new bugs.

Lessons learned

Overall, TDD offers a solid way to write high quality code. Though it takes longer time, but compared to traditional development, it saves time in finding bugs. In addition, TDD makes developers be confident with their codes - the test result proves that all commits are correct.

---
7. Design Improvement

“Don’t let the sun set on bad code. Keep the code as clean and expressive as possible”

How was this applied in your team?
We make a code review process on GitHub to make sure the code is clean and readable. Every commit which will be merged to the master branch needs a pull request. In the pull request, we make a code review on the code. If the code is not clean, we will reject the pull request. In addition, because the code became more complex than we expected. Therefore, to make the whole project to be clean and readable, we had to make a partial code refactoring in the middle of the project.

Benefits
1. It is friendly to code long term maintenance/support, because we are keeping the code clean and readable. Once the code is unreadable, we have to split the code into smaller pieces.

2. Making the code to be high cohesion and low coupling is a widely accepted good practice. It can generallly increase the speed of development in a continuous delivery project. 

Drawbacks
1. Making code clean and refactoring is always time-consuming. Though it is good for our project and worth the time cost, it still makes us tired.

Lessons learned
In XP, design improvement is a continuous process. We can always make our code to be better. As in XP, we also use TDD, thus, in every code refactoring, we don't need to worry about breaking the whole system. The XP practices support each other, they are stronger together than separately.

---
8. Continuous Integration

“The team keeps the system fully integrated at all times”

How was this applied in your team?
CI is a very popular concept in XP. We use Github Workflow to run the tests and build the code. Once a pull request is merged to the master branch, the workflow will be executed. Basically we build the code with tests firstly, then we build a docker image with the built executables and push them to ghcr.io, the official github docker image repository. 

Benefits
1. CI can help us to build the code in a easier way. We don't need to build in every time manually. We just upload the code, have a cup of coffee, and wait for the CI to build the code.

2. CI can provide strict steps and a high reliability of building and deploying the code. For example, if we do the build step mannually, we may forget some steps, like run the test first, or build a wrong docker image. CI are going to make sure every steps are correctly executed.

Drawbacks
1. Writing CI scripts require a certain knowledge of the system. We need to know how to build the system manually, how to run the tests, how to build the docker image, and how to push the image to the official repository. Then, we can start to write the scripts.

2. CI scripts are also hard to debug, because everythings are executed on the cloud, we can just use print statements to debug the scripts.

Lessons learned
CI is a good practice to build the code in a easier way. CI is also a good practice to provide strict steps and high reliability of building and deploying the code. Using CI may spend more time at the early stage, but it does save a lot of time.

---
9. Collective Code Ownership

“Any pair of programmers can improve any code at any time”

How was this applied in your team?

Benefits

Drawbacks

Lessons learned

---
10. Coding Standard

“All the code in the systems looks as if it was written by a single – very competent – individual”

How was this applied in your team?
We use a coding standard to make sure the code is clean and readable. On backend side, we use the PEP8 standard. On frontend side, we use the ESLint standard. The linter guranatees that there's no dummy code and dead code, not used code will raise errors. 

Moreover, on the design level, We strictly require team members split the code into modules. For example, we unbound the entities, response, and request. Moreover, the database queries are also put in separate modules which allows us to easily test the database queries.

Benefits
1. It is a good practice to make sure the code is clean and readable.

2. Well-defined code standard can avoid bugs in the development, since confusing code will not be merged to the master branch.

3. A good coding standard can also help us in refactoring the code as we already write the code in a good way, we can pay a smaller cost to refactor the code, compared to the cost of refactoring a chaotic code.

Drawbacks
1. Keeping the code be clean and following the code standard strictly are also a cost.

2. It is not a universal solution. We cannot expect it to solve all the problems.

Lessons learned
A good coding standard can not only make the system stronger, but also be friendly to the future development. Code in a well-defined coding standard can be easily read by other developers, and other developers can easily understand the code.


---
11. Metaphor

“The team develops a common vision of how the program works”

How was this applied in your team?
Metaphor is a way to describe the requirements of the system, as clients and regular users are also a part of XP. So, instead of using complex technical terms, we use simple words which can be understood by average users to describe the requirements and documentations, i.e. we don't use technical terms in our use cases documents, and we call this project is somewhat similar to a "shared uber" project.

Benefits
1. Use simple words to describe problems and requirements is easy to understand for clients, it can avoid confusion.

2. Because we use unified stories and descriptions, during development, we can easily understand what other developers are saying and doing. Avoiding misunderstandings can save a lot of time on wrong decisions.
 
Drawbacks
1. It's hard to force all group members to use the same term. It takes time to let everyone know the terminology we will be using at the beginning of the project.

2. Metaphors can also be abused. Using metaphors is not for showing off beautiful words to impress customers, it's for keeping users engaged in the development process.

Lessons learned
To get the team to be as productive as possible, we make every members understand it is a very important practice and it can help our to achieve the project goals.

---
12. Sustainable Pace

“The team is in it for the long term. They work hard, at a pace that can be sustained indefinitely. They conserve their energy, treating the project as a marathon rather than a sprint”

How was this applied in your team?
Sustainable pace is a way to make sure the team is working on the project for the long term. We set multiple milestones and for each milestone, we set multiple tasks. In this way, we won't get lost in the middle of the project. i.e. we firstly finish the login/register logics, then finish the journey-related tasks, finish the message-related tasks, and finally finish mapbox display. We check the progress of the project every day. Once the progress is not as expected, we will put other tasks away and have a meeting to find out a solution or a workaround.

Benefits
1. Sustainable pace can make the development process be stable and predictable. Each component of the project is well-planned, and developers are confident with the progress of the project.

2. Sustainable pace is friendly to developers, as it makes sure we will not feel tried in this project. If we push progress back and expecting to finish them in a short period with a high workload, we will highly possible to get a bad result.

Drawbacks
1. It's hard to guarantee all tasks will be finished in time. Exceptions are also possible, once it happens, we have to spend time out of the schedule to find a solution, like having an extra meeting on Saturday or Sunday.

2. Abilities of different developers are different, we need familiar with each other to make sure we can allocate reasonable tasks to each one. Mis-task-allocation can block the progress of the project.

Lessons learned
Increasing the number of work hours does not linearly increase productivity, and every programmer has a "Maximum Productivity" which depends on his talent, then there is something called "Current Capacity" which is the highest amount of work that can be done currently without lowering the quality. Instead of keeping the work every week at a high workload, we would better follow a sustainable pace and the results could be surprising.


---
13. Overall Project


Benefits

Drawbacks

Lessons learned



