# Shops Catalog application
Shops catalog, using Spring boot and Spring security with MongoDB

# How to deploy

## 1- MongoDB
* Install [MongoDB](https://www.mongodb.com/download-center#community)
* After Install :
  - Run the MongoDB server using port 50123 `mongod --port 50123`
  - Restoring shops database :
    -Unzip [Dump](https://github.com/abdessamad-ab/shops_spring/blob/master/shops.rar)
    -Go to the unzipped directory and run `mongorestore --db shops --port 50123 shops`
    
## 2- Running maven project

### Using Maven :
[building Java applications](http://www.vogella.com/tutorials/ApacheMaven/article.html)

### Using your favorite IDE :
- Open project in your IDE
- Clean and build
- Run
