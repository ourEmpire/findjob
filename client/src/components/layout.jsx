import '../sass/layout.scss';
function Layout(props) {
    return (
        <div className="findjob-layout">
            {props.children}
        </div>
    );
}

function Header(props) {
    return (
        <header className="findjob-header">
            <div className="content">
                {props.children}
            </div>
        </header>
    );
}

function Content(props) {
    return (
        <section className="findjob-content">
            <div className="content">
                {props.children}
            </div>
        </section>
    );
}

function Footer(props) {
    return (
        <footer className="findjob-footer">
            <div className="content">
                {props.children}
            </div>
        </footer>
    );
}

export { Layout, Header, Content, Footer };