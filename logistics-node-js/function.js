const Kafka = require('no-kafka');

const topic = 'order-creation';

const consumer = new Kafka.SimpleConsumer({"connectionString":"127.0.0.1:9092"})

const data = function(messageSet) {
    messageSet.forEach((m) => {
        const order = JSON.parse(m.message.value.toString('utf8'));
        console.log('Received Order: '+order.id);
        console.log('Consulting at products Microservice the products: '+order.products);
        setTimeout(() => {
            console.log('Products are available, dispatching to Shipping microservice with ID = '+order.addressId+ ' to client: '+order.clientName);
        }, 5000);
    })
}

consumer.init().then(function() {
    return consumer.subscribe(topic, 0, {time: Kafka.EARLIEST_OFFSET}, data);
})