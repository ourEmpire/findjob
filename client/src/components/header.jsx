import { Icon } from 'antd';
import conf from '../config/header.yml';
import '../sass/header.scss';
import { Link, BrowserRouter as Router } from 'react-router-dom';

let Header = props => {
    let items = conf.header.children;
    let content_left = [];
    let content_right = [];
    _.each(items, (v,k) => {
        let pos = v.position;
        let result = (
            <div key={`header-${k}`}>
                <span key={`header-${k}-icon`}><Icon type={v.icon}/></span>
                <Link className="link-a" key={`header-${k}-text`} to={v.path}>{v.text}</Link>
            </div>
        );
        if(pos==="right") content_right.push(result);
        else content_left.push(result);
    });

    return (
        <div className="findjob-comp-header">
            <div className="header-left">
                {content_left}
            </div>
            <div className="header-right">
                {content_right}
            </div>
        </div>
    );
}

export default Header;