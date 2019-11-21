import '../sass/qr.scss';
class Qr extends Component {

    constructor(props){
        super(props);
        this.state = {
            img: null,
            id: null,
            isLoaded: false
        };
    }

    componentWillMount(){
        $.get($conf.api.login.wechat,data => {
            this.setState({
                img: `data:image/jpeg;base64,${data.img}`,
                id: data.id
            });
        },'json')
    }

    render(){
        return (
            <div className="qr">
                {this.state.isLoaded?null:<img className="qr-img" src={this.state.img}/>}
            </div>
        );
    }
}

export default Qr;