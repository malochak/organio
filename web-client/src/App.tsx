import React, {useState} from 'react';
import './App.css';
import {Col, Container, Row} from "react-bootstrap";

const App = () => {
    const [toggled, toggle] = useState(true);
    const toggleSidebar = () => {
        console.log("toggle")
        toggle(!toggled)
    }

    return (
        <div className={!toggled ? "body-pd" : ""}>
            <header className={toggled ? "header" : "header body-pd"} id="header">
                <div className="header__toggle">
                    <i
                        className={toggled ? "bx bx-menu" : "bx bx-menu bx-x"}
                        id="header-toggle"
                        onClick={toggleSidebar}
                    />
                </div>

                <div className="header__img">
                    <i className='bx bx-layer' id=""/>
                </div>
            </header>

            <div className={toggled ? "l-navbar" : "l-navbar show"} id="nav-bar">
                <nav className="nav">
                    <div>
                        <a href="#" className="nav__logo">
                            <i className='bx bx-layer nav__logo-icon'/>
                            <span className="nav__logo-name">Organio</span>
                        </a>

                        <div className="nav__list">
                            <a href="#" className="nav__link active">
                                <i className='bx bx-grid-alt nav__icon'/>
                                <span className="nav__name">Dashboard</span>
                            </a>

                            <a href="#" className="nav__link">
                                <i className='bx bx-user nav__icon'/>
                                <span className="nav__name">Users</span>
                            </a>

                            <a href="#" className="nav__link">
                                <i className='bx bx-message-square-detail nav__icon'/>
                                <span className="nav__name">Messages</span>
                            </a>

                            <a href="#" className="nav__link">
                                <i className='bx bx-bookmark nav__icon'/>
                                <span className="nav__name">Favorites</span>
                            </a>

                            <a href="#" className="nav__link">
                                <i className='bx bx-folder nav__icon'/>
                                <span className="nav__name">Data</span>
                            </a>

                            <a href="#" className="nav__link">
                                <i className='bx bx-bar-chart-alt-2 nav__icon'/>
                                <span className="nav__name">Analytics</span>
                            </a>
                        </div>
                    </div>

                    <a href="#" className="nav__link">
                        <i className='bx bx-log-out nav__icon'/>
                        <span className="nav__name">Log Out</span>
                    </a>
                </nav>
            </div>

            <Container fluid >
                <Row>
                    <Col md={5}>
                        <b>Todos</b>
                    </Col>
                    <Col md={7}>
                        <b>Calendar</b>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default App;
