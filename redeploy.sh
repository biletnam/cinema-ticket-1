javac -cp /usr/share/tomcat8/lib/servlet-api.jar -d /home/mabysh/devel/javaprj/cinematicket/WebContent/WEB-INF/classes /home/mabysh/devel/javaprj/cinematicket/src/cinematicket/*
#javac -d WebContent/WEB-INF/classes src/cinematicket/*
sudo chown $USER:$USER /home/mabysh/devel/javaprj/cinematicket/WebContent/WEB-INF/classes/cinematicket
sudo chown $USER:$USER /home/mabysh/devel/javaprj/cinematicket/WebContent/WEB-INF/classes/cinematicket/*
jar cfv deploy/cinematicket.war -C WebContent .
sudo rm -r /usr/share/tomcat8/webapps/cinematicket*
sudo cp deploy/cinematicket.war /usr/share/tomcat8/webapps
sudo systemctl restart tomcat8

