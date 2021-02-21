import React from 'react';
import './App.css';
import {Col, Container, Row} from "react-bootstrap";


function App() {
  return (
          <Container fluid>
              <Row className="main-content">
                  <Col md={3} className="brdr">
                      <b>Sidenav</b>
                  </Col>
                  <Col md={4} className="brdr">
                      <b>Todos</b>
                  </Col>
                  <Col md={5} className="brdr">
                      <b>Calendar</b>
                  </Col>
              </Row>
          </Container>
  );
}

export default App;
