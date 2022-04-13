# Use cases

### 1. Account Registration

---
Use case name: 
Account Registration

---
Description:
The app allows users to register an account. If a username already exists, the user should be notified.

---
Actors:
Users, Registration Controller

---
Trigger:
User clicks the “Register” button.

---
Input:
User enters a username, a password, and a bio.

---
Flow of events:
1. User inputs a username, a password, and a bio.
2. User clicks the “Register” button.
3. System checks if the username already exists.
4. 
    1. If the username already exists, the user should be notified. 
    2. If the username does not exist, the user should be registered.

---
Specifial requirements:
N/A

---
Preconditions:
N/A

---
Postconditions:
N/A


### 2. Account Login

---
Use case name: 
Account Login

---
Description:
The app allows users to login to their account. If the username or password is incorrect, the user should be notified.

---
Actors:
Users, Login Controller

---
Trigger:
User clicks the “Login” button.

---
Input:
User enters a username and a password.

---
Flow of events:
1. User inputs a username and a password.
2. User clicks the “Login” button.
3. System checks if the username and password are correct.
4. 
    1. If the username and password are correct, the user should be logged in. 
    2. If the username and password are incorrect, the user should be notified.

---
Specifial requirements:
N/A

---
Preconditions:
N/A

---
Postconditions:
The system will allow the user to view/update/create their profile and proceed with the journey planning.

### 3. Finding nearby travellers within reasonable distance

---
Use case name:
Finding nearby travellers within reasonable distance

---
Description:
The User will provide the starting location and destination points to the app and the app will start searching for the travelers within 500 m of the user and is traveling to the destination within the reasonable range.

---
Actors:
Users, Journey Controller

---
Trigger:
User fill the departure and distination location.

---
Input:
User enters a departure location and a destination location.

---
Flow of events:
1. User enters a departure location and a destination location.
2. System checks if the location is valid.
  1. If the location is valid, the system will start searching for the travelers within 500 m of the user and is traveling to the destination within the reasonable range.
  2. If the location is invalid, the system will notify the user.
3. System searches for nearby travellers.
4. System returns the list of nearby travellers.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in.

---
Postconditions:
User can select one journey from the list of nearby journeys and join the journey.

### 4. Navigating to the destination 
4. Navigating to the destination 

---
Description:
The user can get a suggested route from the starting point to the destination.

---
Actors:
Users, Journey Controller

---
Trigger:
User clicks the “Get Route” button.

---
Input:
N/A (App can automatically upload the users' current location)

---
Flow of events:
1. 1. Wait the host starts the journey.
   2. The host user starts the journey.

2. The route shows on the map.

3. User moves and map updates.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in and should be in a journey.

---
Postconditions:
N/A

### 5. SOS Alert
5. SOS Alert

---
Description:
The User will be able to alert the emergency response services in case of emergencies.

---
Actors:
Users

---
Trigger:
User clicks the “SOS” button.

---
Input:
N/A

---
Flow of events:

1. User clicks the “SOS” button.
2. System sends an alert to the emergency responsibles.

---
Specifial requirements:
N/A

---
Preconditions:
N/A

---
Postconditions:
N/A

### 6. Passenger leave a journey
6. Passenger leave a journey

---
Description:
The User will be able to leave a journey.

---
Actors:
Users, Journey Controller

---
Trigger:
User clicks the “Exit” button.

---
Input:
N/A

---
Flow of events:

1. User clicks the “Exit” button.
2. System removes the user from the journey.

---
Specifial requirements:
N/A

---
Preconditions:
User should log in and be in a journey.

---
Postconditions:
N/A

### 7. User create a journey
7. User create a journey

---
Description:
The User will be able to create a journey.

---
Actors:
Users, Journey Controller

---
Trigger:
User clicks the “Create” button.

---
Input:
User enters a departure location and a destination location.

---
Flow of events:
1. User enters a departure location and a destination location.
2. User clicks the “Create” button.
3. System creates a journey.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in, and search for a journey first.

---
Postconditions:
N/A

### 8. User join a journey
8. User join a journey

---
Description:
The User will be able to join a journey after searching for a journey.

---
Actors:
Users, Journey Controller

---
Trigger:
User clicks the “Join” button.

---
Input:
N/A

---
Flow of events:
1. User clicks the “Join” button.
2. System adds the user to the waiting list of the journey.
3. System sends an alert to the host user.
4. Host user accepts the user to the journey.
5. System moves the user to the journey members list.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in, and search for a journey first.

---
Postconditions:
N/A

### 9. Host user accept a user to the journey
9. Host user accept a user to the journey

---
Description:
The Host user will be able to accept a user to the journey.

---
Actors:
Users, Host User, Journey Controller

---
Trigger:
The host user clicks the “Accept” button.

---
Input:
N/A

---
Flow of events:
1. The host user get the waiting list of the journey.
2. The host user clicks the “Accept” button.
3. System moves the user to the journey members list.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in and be a host of a journey.

---
Postconditions:
N/A

### 10. Host user start a journey
10. Host user start a journey

---
Description:
The Host user will be able to start a journey.

---
Actors:
Users, Host User, Journey Controller

---
Trigger:
The host user clicks the “Start” button.

---
Input:
N/A

---
Flow of events:
1. The host user clicks the “Start” button.
2. System starts the journey.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in and be a host of a journey.

---
Postconditions:
N/A

### 11. Users confirm theirselves as "arrrived"
11. Users confirm theirselves as "arrived"

---
Description:
The User will be able to confirm their arrival if they are arrived at the destination or they want to leave the journey on a halfway point.

---
Actors:
Users, Journey Controller

---
Trigger:
User clicks the “Arrived” button.

---
Input:
N/A

---
Flow of events:
1. User clicks the “Arrived” button.
2. System marks the user as arrived.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in, be in a journey, and the journey should be started.

---
Postconditions:
N/A

### 12. User send a message to others in the journey
12. User send a message to others in the journey

---
Description:
The User will be able to send a message to others in the journey. The message is end-to-end encrypted, only the sender and the receiver can read the message.

---
Actors:
Users, Message Controller

---
Trigger:
User clicks the “Send” button.

---
Input:
User enters a message text.

---
Flow of events:
1. User click a user's avatar.
2. User clicks the “Send Message” button in the profile page.
3. User enters a message text.
4. User clicks the “Send” button.
5. System encrypts the message.
6. System sends the message to the receiver.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged in and in the journey searching result page.

---
Postconditions:
N/A

### 13. User receive a message from others
13. User receive a message from others

---
Description:
The User will be able to receive a message from others. The message is end-to-end encrypted, only the sender and the receiver can read the message.

---
Actors:
Users, Message Controller

---
Trigger:
User click the Message notification to get in a chat page

---
Input:
N/A

---
Flow of events:
1. User click the Message notification to get in a chat page
2. System decrypts the message locally.
3. System displays the message in the chat page.

---
Specifial requirements:
N/A

---
Preconditions:
User need to be logged.

---
Postconditions:
N/A



