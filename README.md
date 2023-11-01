# Order Manager API

The Order Manager API is a robust and scalable RESTful web service that empowers users to create and manage orders efficiently. This API is built with Java 8, using the Spring Boot framework, Spring Data JPA for database interactions, and PostgreSQL as the database system. It also utilizes Git for version control and log4j for logging.

## Features

The Order Manager API offers a wide range of features to streamline your order management processes:

- **Create Order**: Users can easily create new orders specifying items, quantities, and associated users.

- **Read Order**: Retrieve detailed information about existing orders, items, and users. Fetch orders by their unique IDs or view all orders in the system.

- **Update Order**: Modify order details, including items and quantities. Make real-time updates as needed.

- **Delete Order**: Remove orders from the system when they are no longer required.

- **Automatic Order Fulfillment**: Orders are automatically fulfilled as soon as the item stock allows it. No manual intervention required.

- **Stock Management**: Keep track of stock movements, including creation dates, associated items, and quantities. Ensure accurate inventory management.

- **User Notifications**: Users who create orders receive email notifications when their orders are successfully completed.

- **Traceability**: Easily trace the list of stock movements used to complete each order.

- **Real-time Order Completion Status**: Monitor the current completion status of each order, ensuring visibility and transparency.

- **Logging**: The API generates detailed log files, including records of orders completed, stock movements, email notifications, and error messages for debugging and analysis.

## Entities

The Order Manager API operates with the following core entities:

- **Item**: Represents items with names. Used for creating orders and tracking stock.

- **Stock Movement**: Records stock movements, including their creation dates, associated items, and quantities.

- **Order**: Represents orders placed by users. Contains creation dates, items, quantities, and user information.

- **User**: Represents users with names and email addresses who create orders.

## Requirements

To run the Order Manager API, you will need the following technologies and tools:

- Java 8
- Spring Boot and Spring Data JPA
- PostgreSQL database
- Git for version control
- Log4j (or other logging framework)

## How to Run the Project

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/order-manager.git

2. Navigate to the project directory:
   ```bash
   cd order-manager
   
4. Build and run the project:
   ```bash
   ./mvnw spring-boot:run

## API Routes

The API offers a set of routes to interact with the system:

### Order Routes

- **Create Order**: `POST /order`
- **Get Order by ID**: `GET /order/{id}`
- **Update Order by ID**: `PUT /order/{id}`
- **Delete Order by ID**: `DELETE /order/{id}`
- **List All Orders**: `GET /order`

### Item Routes

- **Create Item**: `POST /item`
- **Get Item by ID**: `GET /item/{id}`
- **Update Item by ID**: `PUT /item/{id}`
- **Delete Item by ID**: `DELETE /item/{id}`
- **List All Items**: `GET /item`

### Stock Movement Routes

- **Create Stock Movement**: `POST /stock-movement`
- **Get Stock Movement by ID**: `GET /stock-movement/{id}`
- **Update Stock Movement by ID**: `PUT /stock-movement/{id}`
- **Delete Stock Movement by ID**: `DELETE /stock-movement/{id}`
- **List All Stock Movements**: `GET /stock-movement`

### User Routes

- **Create User**: `POST /user`
- **Get User by ID**: `GET /user/{id}`
- **Update User by ID**: `PUT /user/{id}`
- **Delete User by ID**: `DELETE /user/{id}`
- **List All Users**: `GET /user`
- 
## Unit Testing

To ensure the robustness of the Order Manager API, the project includes comprehensive unit tests for the service and controller components. These tests validate the functionality and behavior of the API, providing confidence in its reliability.

You can run the unit tests using your preferred testing framework or the one integrated with your development environment.
