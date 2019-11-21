import '../sass/search.scss';
import { Icon } from 'antd';
import Study from '../components/study.jsx';

class Search extends Component {
    
    constructor(props){
        super(props);

        this.state = {
            search: React.createRef(),
            value: null,
            data: [],
            way: null
        };
    }

    input = e => {
        this.updateValue(e.target.value)
    }

    updateValue(value){
        this.setState({
            value
        });
    }

    search = e => {
        let keyword = this.state.value;
        $.getJSON(`${$conf.api.search.info}?jobName=${keyword}`,res => {
            this.setState(state =>({
                data: res.data.companies,
                way: res.data.node
            }));
        })
    }

    componentDidMount(){

    }


    render(){
        let result = this.state.data.map((v,i) => {
            return (
                <Item key={`card-${i}`} data={v}  />
            );
        });
        let way = this.state.way;
        let studyWay = <Study key={way?way.name:'null'} data={this.state.way} />;
        return (
            <div className="page-search">
                <div className="search-ic">
                    <input placeholder="输入职位" onChange={this.input} ref={this.state.search} type="text" className="search-ic" />
                    <Icon type="search" onClick={this.search} style={{fontSize: 30}}/>
                </div>
                <section className="search-result">
                    <div className="search-job-cards">
                        {result}
                    </div>
                    <div className="study-way">
                        {this.state.way?studyWay:null}
                    </div>
                </section>
            </div>
        );
    }
}


function Item(props) {
    let data = props.data;
    return (
        <div className="search-job-card">
            <a className="company-name" href={data.href}>{data.companyName}</a>
            <hr />
            <div>工作名: {data.jobName}</div>
            <div>地点: {data.locate}</div>
            <div>薪酬: {data.salary}</div>
        </div>
    );
}

export default Search;