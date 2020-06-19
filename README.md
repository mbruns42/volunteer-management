# volunteer-management

Basic tasks:
- [x] Install database 
- [x] Create database scheme
- [x] Create simple table view for volunteer data
- [x] Allow switching views
- [ ] Get dummy data in clients format 
- [ ] Create data import
- [ ] Allow data modification for volunteers
- [ ] Create automatic export of data after update
- [ ] Create start page with most commonly needed info (upcoming birthdays and anniversary)
- [ ] Get feedback from users

Nice to have:
- [ ] Mailing list creation view
- [ ] Figure out way to make configure interface translation
- [ ] Scheduling view


Technical implementation:
* Uses gradle as a build system
* Uses MariaDb and connects to it using JDBC
* Instead of implementing own user/password storage, use existing MariaDB user mechanism and offer GUI to automatically connect to it
* The user name in MariaDB will also be used for the Inserted by and Updated By fields
* Comes with sql scripts to set up corresponding database scheme
