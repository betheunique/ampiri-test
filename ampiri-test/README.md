Spring Boot Application - Mobile Specific Advertisement Dimension Web Services

## Application SetUp:
This is SpringBoot application with embedded Tomcat. MySQL as dataSource.
Create a DB and import the db.sql from com.glispa.ampiri.sql

Change these properties value from {application.properties} based on your configuration :

spring.datasource.url=jdbc:mysql://localhost:3306/ampiri-test
spring.datasource.username=root
spring.datasource.password=root

## BUILD & RUN
mvn clean install
java -jar target/ampiri-test-0.1.0-SNAPSHOT.jar

Plug n Play API end Points.

1. Advertisement request based on App screen Size.
MySQl DataBase used for persistence.

Tables
t_app responsible for App information.
t_ad_place responsible for ad information with attached app. One-to-Many relationship with t_app.
t_dimension responsible for pre-defined ad dimension.

Few assumption.
If max , min height or width is 0 means app doesn't have upper or lower limit.
App Banned = 1 means ad will be not displayed on this app.

It returns dimension based on floor & ceiling if requested height and width is not in pre-defined dimension.
Main functionality lies in Sorted array combination of MaxHeight, MinHeight, requestedHeightFloor & requestedHeightCeiling same for width also.

Generic Routing Start with version numbering - "/v1"
End point : /v1/serveAd

Sample Request Body : {
               	"addId":"1f855d85-6b3b-4113-af6b-c87b1b39e185",
               	"height": 1200,
               	"width": 700
               }

Sample Response : {
                    "url": "http://cdn303.example.com/video/codec/mp4/1280x720.mp4",
                    "type": "video"
                  }

Advertisement Dimensions are pre- defined, Stored in DB and In-APP cache.
It Can be updated, added and removed at run time with end point.
End points : /v1/addAdDimension
            /v1/updateAdDimension
            /v1/removeAdDimension?adDimensionId=
            /v1/allAdDimension

1. /v1/addAdDimension
Sample Request : {
                 	"height": 1200,
                 	"width": 700
                 }
Sample Response : {
                    "status": 0,
                    "message": "New Ad dimension added successfully"
                  }

2. /v1/UpdateAdDimension
Sample Request : {
                 	"id": 6,
                 	"height": 900,
                 	"width": 600
                 }

Sample Response : {
                    "status": 0,
                    "message": " Ad dimension Update successfully"
                  }

                  failure response :
                  {
                    "status": -1,
                    "message": " Unsuccessful"
                  }

3. /v1/removeAdDimension?adDimensionId=
Sample Request : /v1/removeAdDimension?adDimensionId=6

Sample Response : for success 1
                  for failure 0

4. /v1/allAdDimension

Sample Response : [
                    {
                      "id": 1,
                      "height": 320,
                      "width": 240
                    },
                    {
                      "id": 2,
                      "height": 640,
                      "width": 480
                    },
                    {
                      "id": 3,
                      "height": 1280,
                      "width": 720
                    },
                    {
                      "id": 4,
                      "height": 1440,
                      "width": 900
                    },
                    {
                      "id": 5,
                      "height": 1200,
                      "width": 700
                    }
                  ]

````````````` Extra End Points ``````````````````````

End Point for adding new App & AdPlace
1. /v1/addApp
Sample Request : {
                 	"appId": "3e95de7a-12c3-431f-b6dd-fe19623a3893",
                 	"banned": 0,
                 	"maxHeight": 600,
                 	"minHeight": 400,
                 	"maxWidth": 320,
                 	"minWidth": 240
                 }

Sample Response : {
                    "status": 0,
                    "message": "New App added successfully"
                  }

2. /v1/addAdPlace
Sample Request :{
                	"adPlaceId": "9f855d85-6b3b-5223-af6b-c87b1b39e295",
                	"appId": "3e95de7a-12c3-431f-b6dd-fe19623a3893",
                	"adType": "image"
                }

Sample response : {
                    "status": 0,
                    "message": "New Ad placed successfully"
                  }