# Library Material Reservation Application J2EE - (LiMaRA)
## Overall Project Summary
Develop a J2EE web application that provides library material reservations for a city library. The
application consists of jspx pages, servlets and/or filters, css files, event listeners, java bean
components, and java data components and will provide navigation between them through the use of
buttons and links.

When the application first opens, the limousine client will be presented with a login screen. They will
provide their login information. The application will authorize their access to the site after confirming
their credentials against those stored in a database. After logging in, the library account owner is
presented with a library material reservations screen.

**The library account owner can perform the following operations:**
1. Create a new library account
2. View existing library material reservations
3. Cancel a library material reservation
4. Reserve library materials
5. Exit the application (logout)

### Create a New Library Account
From the login screen, a non library account owner has the opportunity to create a new library account by
clicking on a **New Library Account** link. The non library account owner provides a first name, last
name, username, and password along with confirm password and then clicks the **Create Library
Account** button. The new library account owner is then taken to the existing library material reservations
page where the (initially empty) reservations list is displayed for the new account.
### View Existing Library Material Reservations
Once the library account owner has logged in, they are presented with a screen showing a list of the
account owner's current library material reservations (material_id, type, reservation expiration date).
Along with each reservation is a Cancel Reservation button. To cancel a library material reservation, the
account owner would simply click the **Cancel Reservation** button. The account owner must confirm the
library material reservation cancellation. After confirming the cancellation of the library material
reservation, the account owner is presented with an updated list showing that the library material
reservation has been cancelled.
### Reserve Library Materials
The library account owner will use the reserve library materials page to reserve library materials. The
page allows a title (or partial title) and the desired type of library material to be specified. The type of the
library material should be a drop down list. For example: **All, Book, Audio, Video** (add other
types if appropriate). **All** should be the default (first in the list). Finally, a **Check What's
Available** button can be clicked to search for any available library materials matching the specified
criteria. After the Check What's Available button is clicked, the search results are listed on the _same
page_ (below the search criteria). Beside each listed library material is a Reserve This button. The library
account owner can simply click the **Reserve This** button to reserve the library material.
### Logging Out
When the library account owner logs out, the application terminates the library account owner's session
and returns to the login screen.
### The Library Materials
The several library materials will be fixed and prepopulated in the database in Stage 3 of the application.
### Using Subversion
The subversion software repository is used for shared development work.
### Project Stages
The project is divided into three stages.
1. **UI Page Design and Navigation:** For this stage we determine the XHTML structure of the
pages and apply any desirable CSS styles. The pages will contain mock data to show off the page's
structure and presentation features. Simple static page navigation is implemented.
2. **Implementation of HTML Forms, Error Pages, Servlets/Filters, and the MVC Framework:**
For this stage, we will design and implement the MVC framework of the application. The MVC framework provides a mechanism for user feedback (provided via HTML forms) to be processed by the java code and then have the results passed on to a JavaServer Page
for display. For this stage, we will stub the data portion of the application to provide faked data.
3. **Session State, JSTL, and JDBC:** For this stage, we will finalize the implementation adding
session state to hold the user information, JSTL to provide dynamic responses depending on the
state of the application, and JDBC to access the data storage for the application.
