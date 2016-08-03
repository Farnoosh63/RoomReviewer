# Room Reviewer

#### An Application that reviews rooms, May 20, 2016

#### By Fernanda Lowe, Farnoosh Johnson, Reed Lambier, Scott McIntire

## Description

This application let users write reviews on rooms they are or have rented, or just read one, as well as:

1. search for reviews
2. add, update, delete a review


####Screenshots of some pages of the app:
<img id="screen-1" src="https://s26.postimg.org/gvdtul4u1/Screen_Shot_2016_08_02_at_5_33_01_PM.png" width="200px" title="main page" />
<img id="screen-1" src="https://s26.postimg.org/jqqx1g8u1/Screen+Shot 2016-08-02_at 5.33.20 PM.png" width="200px" title="add review" />
<img id="screen-1" src="https://s26.postimg.org/i07vzyrax/Screen_Shot_2016_08_02_at_5_33_49_PM.png" width="200px" title="Manage submit record" />
<img id="screen-1" src="https://s26.postimg.org/fwxgsari1/Screen+Shot 2016-08-02 at 5.34.22 PM.png" width="200px" title="search result" />

## Setup/Installation Requirements

* Clone this repository
* Install postgres and connect to psql and run: CREATE DATABASE room_review;
* Create test database: CREATE DATABASE room_review_test WITH TEMPLATE room_review;
* In the command line run this command: psql room_review < room_review.sql
* Run the application in using the command: gradle run.


## Known Bugs

None;

## Support and contact details

If while using this application you run into any issues or have questions, ideas, concerns, or would like to make a contribution to the code, please contact us at contact@roomreviewer.com

## Technologies Used

* java
* Spark, Gradle, jUnit, sql2o, postgresql, Velocity, Fluentlenium-core
* CSS, HTML, Javascript, jQuery, Bootstrap


### License

Application can be used under MIT licenses.

Copyright (c) 2016 **Fernanda Lowe, Farnoosh Johnson, Reed Lambier, Scott McIntire, Epicodus**
