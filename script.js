
dbD = db.getSiblingDB('distribuida');
dbD.createCollection('books');

dbD.books.insertMany( [
    { title: "test-1", isbn: "111",author: 1,price: 2.20},
    { title: "test-2", isbn: "222",author: 1,price: 3.20},
    { title: "test-3", isbn: "333",author: 1,price: 4.20},
    { title: "test-4", isbn: "4444",author: 1,price: 5.20}
] );
