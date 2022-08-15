async function getData(){
    const reqForAllBooks = await fetch('localhost:8080/book/getAll');
    const data = await reqForAllBooks.json();
    data.map((bookDto)=>{
        $('tbody').append('<tr><td>${bookDto.id}</td><td>${book.title}</td><td>${book.author}</td><td>${book.genre}</td></tr>')
    })
}

getData();