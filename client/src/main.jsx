import { Layout, Header, Content, Footer } from './components/layout.jsx';
import HeaderContent from './components/header.jsx';
import {CopyRight, copyright} from './components/footer.jsx';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import {lazy,Suspense} from 'react';
import { Load } from './components/load.jsx';
import Firework from './components/firework.jsx';
import './scripts/custom';
import './sass/main.scss';

const Home = lazy(()=>import('./components/home.jsx'));
const Book = lazy(()=>import('./components/book.jsx'));
const Search = lazy(()=>import('./components/search.jsx'));
const Login = lazy(()=>import('./components/login.jsx'));
const Users = lazy(()=>import('./components/users.jsx'));

let Index = props => {
    copyright();
    let path = document.location.pathname;
    let content = (
        <Layout>
            <Header>
                <HeaderContent />
            </Header>
            <Content>
                <Route exact path='/' component={Home} />
                <Route exact path='/book' component={Book} />
                <Route exact path='/search' component={Search} />
                <Route exact path='/user' component={Users} />
            </Content>
            <Footer>
                <CopyRight />
            </Footer>
        </Layout>
    );

    let login = <Login />;
    return (            
    <Router>
        <Firework />
        <Suspense fallback={<Load />} >
            {path==='/login'?login:content}
        </Suspense>    
    </Router>
    );
}

ReactDOM.render(<Index />,$('#main')[0]);