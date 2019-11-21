
import config from '../config/books.yml'
import '../sass/book.scss';
import Table from '../components/table.jsx';

class Book extends Component {

    componentDidMount(){
    }

    render(){
        let books = items.books;
        let content = books.map((v,i) => {
            return <BookCard key={`bookcard-${i}`} id={v.id} name={v.name} />
        });
        return (
            <div className="page-book">
                {content}
            </div>
        );
    }
}

function BookCard(props) {
    return (
        <div key={`book-card-${props.id}`} className="book-card">
            <div className="book-name">{props.name}</div>
            <img src={`https://file.ourfor.top/source/findjob/book/pic/${props.id}.jpg`} />
        </div>
    );
}


class Bookshelf extends Component {
    componentDidMount(){
    }
    render(){
        const props = this.props;
        let data = config.books;
        let shelf = [];
        let bookboxs = [];
        data.forEach( (v,i) => {
            
            if(i!==0&&i%5===0){
                shelf[0].style = "list-big";
                let books = shelf.map(function(item){
                    let pic = `https://file.ourfor.top/source/findjob/book/pic/${item.id}.jpg`;
                    let book = <BookItem dataStyle={item.style} link={item.href} id={item.id} key={`book-${item.id}`} src={pic} />;
                    return book;
                });

                let bookbox = (
                    <div key={`bookbox-${i}`} className="book-box">
                        <section className="book-list">
                            {books}
                        </section>
                        <section className="book-outline"></section>
                    </div>
                );
                bookboxs.push(bookbox);
                shelf = [];
            }
            shelf.push(v);
        });


        return (
            <div className="learn-source">
                <div id="bookshelf">
                    <div className="book-movies">
                        <div className="book-boxs">
                            <h3 className="title">书籍📚是人类进步的阶梯🏠</h3>
                            {bookboxs}
                        </div>
                    </div>
                </div>                        
                <div className="gap"></div>
                <div className="source-table">
                    <p>🥭 学习资源推荐</p>
                    <Table items={config.source} />
                </div>            
            </div>
        );
    }
}

function BookItem(props) {
    return (
        <div data-style={props.dataStyle} key={props.id} data-id={props.id} className="list-item">
            <img key={props.link} data-link={props.link} src={props.src} />
        </div>
    );
}

export default Bookshelf;