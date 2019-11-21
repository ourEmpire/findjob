import '../sass/loaders.min.css';
import '../sass/load.scss';
class Load extends Component {
    constructor(props){
        super(props);
        this.state = {
            dom: React.createRef()
        }
    }
    componentDidMount(){
    }
    render(){
        return (
        <div className="loader" style={{}}>
            <div className="line-scale-pulse-out">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>
        );
    }
}

export { Load };