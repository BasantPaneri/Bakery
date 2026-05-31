Pass - 12345, 
Email - bakery@gmail.com

linux CMD 
- History
- Permission -------> chmod -x runBakery.sh
- Application  -----> ./runBakery.sh
- start Docker with Sql and setup continer----> docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=demo -p 3306:3306 -d mysql:8.4
- start Docker with Sql when container alread exist------> docker start mysql
- sql on docker ---->  docker exec -it mysql mysql -uroot -p
-

TO DO 
d- after product added to cart we have to reduce the product avialable quantity eather at bill or while adding to cart.
d- tranfer both the create table query to Repo class.
d- add delete function.
-