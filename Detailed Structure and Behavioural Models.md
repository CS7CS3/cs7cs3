# Detailed system structure model

## Main structure

The figure shows the main structure of the system. It consists of the following parts: Runtime environment, Data Storage, Data Abstraction Layer, Functional Layer, View Layer, and the front-end.

The runtime environment is the environment in which the system runs. It is a virtual environment that is used to run the system. Dockers offers this virtual environment, and Kubernetes offers the virtual network structure. In addition, in the docker, JVM with spring boot gives the environment to let the code run.

The database layer is implemented by the MySQL database, we use SQL to read and write data. The MySQL is deployed in the Docker, and for safty reasones , only the localhost can access the database.

The data layer is a abstraction layer of MySQL plain queries. It includes transaction, storage procedures, and other data access methods. And in this layer, we also enabled a log tracker to track the sensitive data access log and error log. Moreover, access control is also implemented in this layer, different roles have different access rights.

Functional layer is the layer that contains the business logic. It includes the business logic of the system. The user management includes the user registration, user login, and user info. The API server offers multiple controllers(user management is also included), e.g. user register/login controller, journey controller, message controller, etc. Moreover, content provider in this layer provides the web app, the system log is used to trace the errors in this system, and the system configuration is used to configure the system during the system boot time.

View layer is built by React.js, it is the front-end of the system. It includes the user interface of the system. It is a single page application, and it access the data dynamically by using the Fetch Request via the API server in the function layer.

The front-end comes from the view layer. The React.JS code will be compiled into plain HTML and CSS, and logics in TypeScript code will be compiled into plain JavaScript.

## Runtime layer

The figure shows the runtime structure of the system. All components are deployed and isolated in the Docker, it is good for the security and the scalability. All communication between the components is done by the virtual network offered by the Kubernetes.

Doker offers the nessesary virtual environment for Kubernetes and other containers.

Kubernetes APIs consists of the following parts: Namespace, Pod, Service, Replication Controller, Deployment, Daemon Set, Stateful Set, Job, Cron Job, Ingress, and Network Policy. We use namespaces to manage the components, pods are the containers to run our code or other upper-layer components, services are the network services(i.e. load balancers), and replication controllers can be used to scale the pods. Deployments are used to manage the replication controllers. Deamon sets, stateful sets, jobs, and cron jobs are used to manage the pods. Ingress and network policies are used to filter the traffic.

Springboot is used to run the code. In Java development, it is called inverse of control, i.e. it is not we call the framework, the framework calls our code. We just write controller classes, service classes, and the rest of the code, and the spring boot framework will automatically run the them.

## Database layer

The database layer is just a deployment of MySQL now, but it can be extended to other database. It can be only accessed by the localhost and the access control and log tracker are implemented in this layer by using the MySQL builtin functions.

## Data layer
To ensure the consistency of the data, we use the transaction to manage the data. The transaction is a set of SQL statements that are executed in a single transaction, the result can only be success or failure. If the result is success, the transaction is committed, otherwise, the transaction is rollbacked.

The storage procedures are used to store the data with complex logic. In this way, we can reduce the complexity of the code. They are implemented in the data layer, and they are used to store the data in the database.

We can also use plain SQL to access the data in this layer, and we also use some custom functions with both SQL and Java code. Moreover, because of the performance considerations, we also use local cache, i.e. we don't need to query the database every time we need the data.

## Function layer

The function layer structure is given below. They are all in the Kubernetes environment.

Node.JS content provider is used to provide the web app, i.e. user enters the url in a browser, the content provider will return the web app. And the middleware is used offer a unified API for the web app in JavaScript style.

In the spring boot part, we have multiple controllers and multiple entities. The controllers are used to handle the requests from the web app, and the entities are used to manage the data. In the entities part, The top layer classes are directly coresponding to the entities in the database, and the two generic classes for requests and responses separate the business logic from the data access.

## View layer

The view layer is built by React.JS which is based on virtual DOM, and it is used to render the web app. The virtual DOM is a tree structure, and it is used to compare the DOM tree with the new DOM tree, and only the changed parts will be updated.

In the libs, we intergrate multiple data sources, such as the data from the API server, the data from the map data provider and the data from the local cache.

## Front-end

The front-end comes from the view layer. It is what users see and interact with. As the image illustrates, it includes HTML, CSS, JavaScript, and other media resources.

# Detailed system behaviour models

## Overview of QoS Technical Requirements 