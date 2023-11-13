# Blogging Platform

## Joining Diversity with Change

Introducing a social blogging platform, which connects users from different walks of life,
holding different experiences through words.The platform provides the ability to users to post
blogs publicly, related to extensive set of topics such as travel, food, lifestyle, news
and many others. It provides a personalised experience to users to
add blogs to their collection, forming a library in their collections which is categorised by type of
blog, and rate the blogs.


With a diverse range of categories such as lifestyle, food, news, fashion and many others, the
platform caters to a diverse set of audience. Users can easily access relevant information related
to desired topics, making the platform accessible and useful for a wide range of people. 
The application is a great source of information for users for their field of
interest from fashion to technology, from travel to health, from world news to lifestyle tips. 
Due to the extensive range of topics covered, this application can turn helpful to people with 
different professions to different age groups, targeting a wide range of audience.


Nowadays, information consumption using video content is dominating. With a very high level of
video consumption, I feel we have formed a drift from reading. Information collection by reading
is a strong way of learning, as words are powerful and hold high value. I want people to start on
with the habit of reading and causing a change in the way information transmits. Other than this, blogs are a great way
to express yourself and convey information.


### User Stories

Here are the user stories for this blogging platform application.

- As a user, I want to be able to write and post different blogs.
- As a user, I want to add blogs to my collection.
- As a user, I want to view the list of blogs added to my collection.
- As a user, I want to sort blogs in my collection according to date .
- As a user, I want to categorise the blogs in my collection
into travel, fitness,lifestyle and business
- As a user, I want to remove blogs from my collection.
- As a user, I want to be able to like blogs and add a review to the blogs.
- As a user, I want to be able to save my blog to the file.
- As a user, I want to be able to be able to load my blog from file.
- As a user, I want to be able to print blogs in my collection.

### Instructions for Grader
- You can generate four buttons by opening the frame - Load Library, Add Blog, Remove Blog, and Print Blogs.
- You can generate the first required action related to adding Xs to a Y by clicking on Add Blog button. By clicking on 
Add Blog button a new window shows up which takes the category of blog, blog name and blog content. The window has an Ad
d button which adds the blog to the library. The window has a Save button which saves the blog to the application file.
- You can generate the second required action related to adding Xs to a Y by clicking the Remove Blog button. This
button opens a new window which takes the blog name and has a remove button. By clicking on the remove button you can
remove the blog from your library. The window also has a save button which saves the state of your application to the
file.
- You can locate my visual component by opening the window. The visual component is present in at the bottom of the four
buttons. You can also locate a visual component by Remove Blog button. The visual component can be seen at the bottom
of the window.
- You can save the state of my application by clicking the Save button present in the Add Blog window and Remove Blog 
window.
- You can reload the state of my application by clicking the Load Library button. After clicking this window a message
pops up which says that the library has been loaded. 


### Phase 4: Task 2

Blog: Travel added to  library

Blog: Gym added to  library

Blog: Health added to  library

Blog: Gym removed from library

### Phase 4: Task 3

The iterator and observer design patterns can be applied to the project for refactoring.
#### Iterator Pattern - 
The iterator design pattern can be applied to the BlogLibrary class. 
The BlogLibrary class can implement iterator interface and which would result in refactoring of 
the for loops present in the BlogApp Class and BlogLibrary class.The methods openblog(), categoriseblog() 
and removeblog() have the for loops and can be refactored by using the iterator pattern. 
#### Observer Design Pattern - 
The observer design pattern can be applied to Blog and Blog Library. 
The Blog Library can be the observable and the Blog can be the observer. The operations like addBlog and 
removeBlog can be added to the subject. This pattern can also be used to notify the users when a new blog is 
added to the library. 



