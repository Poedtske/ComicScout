import * as React from 'react';
import {useState} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Link, Paper} from '@mui/material';
import Button from '@mui/material/Button';
import { useEffect } from 'react';


export default function User() {
    const paperStyle= {padding: '50px 20px', width:600, margin: '20px auto'}
    const[userName,setName]=useState('');
    const[email,setEmail]=useState('');
    const[users, setUsers]=useState([]);
    
    
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

    useEffect(()=>{
        fetch("http://localhost:8080/Users/getAll")
        .then(res=>res.json())
        .then((result)=>{
            setUsers(result);
            console.log(result);
        }
        )
    },[])

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
            </form>
        </Paper>
        <h1>Users</h1>
        <Paper elevation={3} style={paperStyle}>
            {users.map(user=>(
                <Paper elevation={6} style={{margin:"10px", padding:"15px",textAlign:"left"}} key={user.id}>
                    Id:{user.id} <br/>
                    Name:{user.userName}<br/>
                    Email:{user.email}
                    <a href="https://www.kfdemoedigevrienden.be/" target='_blank'><img src={'https://flamecomics.com/wp-content/uploads/2021/11/EXACOVER-242x350.jpg'} height={200}width={150} alt='foto'></img></a>
                </Paper>
            ))}
        </Paper>
    </Box>
    
  );
}
