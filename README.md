# BookChek

## My Personal Reading Log

BookChek offers a reading log for anyone who wants a personalized reading log/planner.
Features include:
- managing a book log, current read(s) and to-read list
- viewing book quote of the day 
- exploring recommended books (manually updated)

*<p>This personal project is aimed to motivate and encourage a steady reading habit for the creator,<br>
as well as anyone who would be interested in using this as a book log.</p>*

## User Stories
- I want to add a book to my to-read list
- From my to-read list, I want to set add current read
- I want to add memorable quotes to a book and a quote book
- I want to view a random quote

- I want to be able to save my to-read list to file
- I want to be able to load my to-read list from file
## Phase 4: Task 2
*Sample of logged events (first 2 user stories):*

No Longer Human was added to Pleasure Reads.<br>
Jane Eyre was added to Homework Reads.<br>
I Want to Die but I Want to Eat Tteokbokki was added to Pleasure Reads.<br>
Saltfish Girl was added to Homework Reads.<br>
I Want to Die but I Want to Eat Tteokbokki was added to current Pleasure Reads.<br>
Saltfish Girl was added to current Homework Reads.<br>

## Phase 4: Task 3
*Reflections*

- Implement bidirectional relationship: Could have implemented a bidirectional relationship between Book and ToRead,
because every book is added to a ToRead object. This could be helpful in certain functionalities that may be added 
later to the application.
For example, if someone wants to search a book object and get its information, it can simply return the book object's
ToRead field, instead of looping through every toRead category to find the matching book that was added.
- Change toReadList and currentReadList to a Set: Could have used a set instead of a list to bar duplicate objects 
being added (trying to bar the same book from being added resulted in duplicate conditional statements in the console 
UI).
- Change ToRead to an abstract class: Since ToRead will always be some specific to-read category, 
ToRead can be an abstract class, and PleasureReads and HomeworkReads can extend ToRead, adding more flexibility to each
subclass.
- Write better variable names: Although the application runs, the code itself can be confusing because so many of the
variable and method names are similar (e.g. backButton and buttonBack), which is prone to making mistakes. 
Could plan accordingly to make variable and method names more consistent and comprehensible.

