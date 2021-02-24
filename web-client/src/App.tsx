import React, {useState} from 'react';
import './App.css';
import {Button, Col, Container, Row} from "react-bootstrap";


const App = () => {
    const [toggled, toggle] = useState(true);
    const toggleSidebar = () => {
        toggle(!toggled)
    }

    return (
        <div className={toggled ? "menuDisplayed" : ""} id="wrapper">
            <div id="sidebar-wrapper">
                <ul className="sidebar-nav">
                    <li>
                        <a href="#">Link 1</a>
                    </li>
                    <li>
                        <a href="#">Link 2</a>
                    </li>
                    <li>
                        <a href="#">Link 3</a>
                    </li>
                </ul>
            </div>

                <Container fluid id="content-wrapper">
                    <Button onClick={toggleSidebar}>Toggle</Button>
                    <Row id="main-row">
                        <Col md={5} className="brdr">
                            <b>Todos</b>
                        </Col>
                        <Col md={7} className="brdr">
                            <b>Calendar</b>
                        </Col>
                    </Row>
                </Container>
        </div>
    );
}

export default App;
