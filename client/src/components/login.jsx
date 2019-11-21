import axios from 'axios';
import { copyright } from './footer.jsx';
import '../sass/login.scss';
import Qr from './qr.jsx';


class Login extends Component {
    constructor(props){
        super(props);
        let show = React.createRef();
        let form = React.createRef();
        let wechat = React.createRef();
        this.state = {
            user: '',
            passwd: '',
            show,
            form,
            showPasswd: false,
            showValue: '显示',
            loadding: false,
            login_wechat: false,
            wechat
        };

    }
    componentDidMount(){
        copyright();
    }
    autoFillStart = event=>{
        let dom = event.target
        let label = $(dom).siblings('label');
        label.addClass('title');
    }
    autoFillCancel = ()=>{
    }   

    setToken = token=>{
        localStorage.setItem('data_token',token);
    }

    getToken = ()=>{
        return localStorage.getItem('data_token');
    }

    logout = ()=>{
        localStorage.removeItem('data_token');
    }

    autoFilled = (event)=>{
        switch(event.animationName){
            case 'onAutoFillStart': this.autoFillStart(event);
            case 'onAutoFillCancel': this.autoFillCancel(event);
            default: ()=>{console.log('autoFilled')}
        }
    }
    handleUser = event=>{
        this.setState({
            user: event.target.value
        });
    }
    handlePasswd = event=>{
        this.setState({
            passwd: event.target.value
        });
        if(event.target.value!==''){
            this.inputFocus(event);
        }
    }
    showPasswd = event=>{
        let isShow = !this.state.showPasswd;
        if(isShow){
            this.setState({
                showPasswd: isShow,
                showValue: '隐藏'
            });
        } else {
            this.setState({
                showPasswd: isShow,
                showValue: '显示'
            });
        }
    }
    login = event=>{
        this.setState({
            loadding: true
        });
        this.handleSubmit(event);
    }

    login_wechat = e => {
        this.setState({
            login_wechat: true
        });
    }

    login_decline = e => {
        this.setState({
            login_wechat: false
        });
    }

    handleSubmit = event=>{
        let token = this.getToken();
        const config = {
           headers: {
                Authorization: `Bearer ${token}`,
           } 
        };
        let params = new URLSearchParams();
        params.append('username',this.state.user);
        params.append('password',this.state.passwd);
        axios.post(this.props.loginAction,params).then(res=>{
            if(res.data.login){
                this.setToken(res.data.token);
                console.log('setToken');
                window.open(this.props.success,'_self')
            }
        }).catch(err=>{
            console.log(err);
        });
    }
    inputFocus = event=>{
        let dom = event.target
        let label = $(dom).siblings('label');
        label.addClass('title');
        if($(dom).attr('type')==='password'&&dom.value!=''){
            $(this.state.show.current).css('display','block');
        }
    }
    inputBlur = event=>{
        let dom = event.target;
        let value = dom.value;
        if(value===''){
            let label = $(dom).siblings('label');
            label.removeClass('title');
        }
    }
    handleMove = event=>{
        $(this.state.show.current).css('display','none');
        this.setState({
            showPasswd: false,
            showValue: '显示'
        });
    }
    render(){
        if(this.state.login_wechat) return (<WechatLogin onClick={this.login_decline} />);
        return (
            <form ref={this.state.form} method='post' action={this.props.loginAction}onSubmit={this.handleSubmit}>
                <div>
                    <div className='input-box'>
                        <label>
                            <input ref={this.state.userInput} type='text' name='username' autoComplete='username' 
                            id='login_user' value={this.state.user} onChange={this.handleUser} 
                            onFocus={this.inputFocus} onBlur={this.inputBlur} required onAnimationStart={this.autoFilled}/>
                            <label htmlFor='login_user' className='place-label'>用户名或电子邮件</label>
                        </label>
                    </div>
                </div>
                <div>
                    <div className='input-box' onMouseLeave={this.handleMove}>
                        <label>
                            <input type={this.state.showPasswd?'text':'password'} name='password' autoComplete='password' 
                            id='login_passwd' value={this.state.passwd} onChange={this.handlePasswd}
                            onFocus={this.inputFocus} onBlur={this.inputBlur} required onAnimationStart={this.autoFilled}/>
                            <label htmlFor='login_passwd' className='place-label'>密码</label>
                        </label>
                        <button type='button' ref={this.state.show} onClick={this.showPasswd} >
                            {this.state.showValue}
                        </button>
                    </div>
                </div>
                <div className="login-select">
                    <div onClick={this.login_wechat} ref={this.state.wechat} login-with="wechat"></div>
                </div>
                <button id="login-submit" type="button" value='登入' onClick={this.handleSubmit}>登入</button>
            </form>
        );
    }
}

class LoginBox extends Component {
    constructor(props){
        super(props);
    }
    render(){
        return (
            <section id='login-box'>
                <h1>登入</h1>
                <Login success={this.props.success} loginAction={this.props.loginAction} />
            </section>
        );
    }
}

function WechatLogin(props){
    return (
        <section className="wechat-login">
            <Qr />
            <button onClick={props.onClick} className="decline">Decline</button>
        </section>
    );
}

function login(){
        return <LoginBox success={"/users"} 
                    loginAction={$conf.api.login.auth}/>
}

export default login;