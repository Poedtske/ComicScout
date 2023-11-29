import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Paper} from '@mui/material';
import Button from '@mui/material/Button';


export default function User() {
    const paperStyle= {padding: '50px 20px', width:600, margin: '20px auto'}
    const[userName,setName]=useState('');
    const[email,setEmail]=useState('');
    const handleClick=(e)=>{
        e.preventDefault();
        const user={userName,email}
        console.log(user);
        fetch("http://localhost:8080/Users",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(user)
        }).then(()=>{
            console.log("User is added");
        })
    }

    return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch',  },
      }}
      noValidate
      autoComplete="off"
    >
        
        <Paper elevation={3} style={paperStyle}>
            <h1 style={{color:"blue"}}><u>Add User</u></h1>
            <form className="User">
                <TextField className='addUser' id="userName" label="User Name" variant="outlined" fullWidth
                value={userName}
                onChange={(e)=>setName(e.target.value)} />
                <TextField className='addUser' id="userEmail" label="Email" variant="outlined" fullWidth
                value={email}
                onChange={(e)=>setEmail(e.target.value)}/>
                <Button className='addUser' variant="contained" onClick={handleClick}>Submit</Button>
                {userName}
                {email}
            </form>
        </Paper>
    </Box>
  );
}
