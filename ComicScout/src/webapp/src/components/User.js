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
    const[series, setSeries]=useState([]);
    //const[chapters,setChapters]=useState([]);
    // const[chapters, setChapters]=useState([])
    
    
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

    const bookmarkClick=(e)=>{
        e.preventDefault();
        let userId=1;
        let serieId=e.target.value;
        

        const ids={userId,serieId}
        //console.log(userId+" "+serieId);
        fetch(`http://localhost:8080/Users/${userId}/Series/${serieId}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(ids)
        }).then(()=>{
            console.log("Bookmarked");
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

        fetch("http://localhost:8080/Series/getAll")
        .then(res=>res.json())
        .then((result)=>{
            if(result){
                setSeries(result);
            }

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
                    Email:{user.email} <br/>
                    Bookmarks:
                    {user.bookmarks
                    .sort((a, b) => a.id-(b.id))
                    .map((bookmark) => (
                        <p>{bookmark.serieName}</p>
                    ))}
                    
                </Paper>
            ))}
        </Paper>
        <h1>Series</h1>
        <Paper elevation={3} style={paperStyle}>
            {series
            .sort((a, b) => a.id-(b.id))
            .map(serie=>(
                <Paper elevation={6} style={{margin:"10px", padding:"15px",textAlign:"left"}} key={serie.id}>
                    Id:{serie.id} <br/>
                    Name:{serie.serieName}<br/>
                    <a href={serie.url} target='_blank'><img src={serie.cover} height={200}width={150} alt='foto'></img></a><br/>
                    <form className="bookmark">
                        
                        <Button className='addBookmark' variant="contained" value={serie.id} onClick={bookmarkClick}>Bookmark</Button>
                    </form>
                    Description:{serie.description}<br/>
                    Chapters:<br/>
                    {serie.chapters
    .sort((a, b) => {
        const getNumericPart = (str) => {
            const matchResult = str.match(/\d+(\.\d+)?/);
            return matchResult ? parseFloat(matchResult[0]) : 0;
        };
        return getNumericPart(a.chapterName) - getNumericPart(b.chapterName);
    })
    .reverse()
    .map((chapter) => (
        <a key={chapter.id} href={chapter.path} target='_blank'>
            {chapter.chapterName}<br />
        </a>
    ))}

                    
                    
                    
                </Paper>
            ))}
        </Paper>
        {/* <h1>Chapters</h1>
        <Paper elevation={3} style={paperStyle}>
            {chapters.map(chapter=>(
                <Paper elevation={6} style={{margin:"10px", padding:"15px",textAlign:"left"}} key={chapter.id}>
                    Id:{chapter.id} <br/>
                    <a href={chapter.path} target=''>{chapter.chapterName}<br/></a>
                </Paper>
            ))}
        </Paper> */}
    </Box>
    
  );
}
