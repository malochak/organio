import React, {Context} from 'react';
import './App.css';

const SecurityContext: Context<any> = React.createContext("");

const App = () => {
    return (
        <SecurityContext.Provider value={"test"}>
            <h1>Organio</h1>
        </SecurityContext.Provider>
    );
}

export default App;
