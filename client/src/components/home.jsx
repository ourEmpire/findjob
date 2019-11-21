import ViewPage from './view_page.jsx';
import '../sass/home.scss';
import loadCalendar from './calendar';
import { Cards } from './card.jsx';
import { Analyse } from './study.jsx';

import Location from './location.jsx';

class Home extends Component {
    componentDidMount(){
        loadCalendar(new Date());
    }
    render(){
        return (
            <div className="home-content">
                <nav className="home-content-msg">
                    <ViewPage />
                </nav>
                <div className="gap"></div>
                <Cards />
                <div className="gap"></div>
                <div className="data-chart">
                    <Location />
                    <Analyse />
                </div>
            </div>
        );
    }
}

export default Home;
