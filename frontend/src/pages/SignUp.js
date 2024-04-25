import React from 'react';
import { useNavigate } from 'react-router-dom';
import { signUp } from '../services/clientServices';
import { useSnackbar } from 'notistack';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import { Link as RouterLink } from 'react-router-dom';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const defaultTheme = createTheme();

export default function SignUp() {
    let navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const name = data.get('name'); 
        const login = data.get('login');
        const password = data.get('password');
        const confirmPassword = data.get('confirmPassword');

        if (password !== confirmPassword) {
            enqueueSnackbar("Passwords do not match", { variant: 'error' });
            return;
        }

        try {
            const response = await signUp(name, login, password);
            if (response.status === 200) {  // Assuming API responds with status 200 on success
                enqueueSnackbar('Registration successful!', { variant: 'success' });
                localStorage.setItem('userId', response.data.id); // Assuming response data has the user ID
                localStorage.setItem('userLogin', response.data.login); // Assuming response data has the user login
                navigate("/home");
            } else {
                enqueueSnackbar('Registration failed!', { variant: 'error' });
            }
        } catch (error) {
            console.error('Sign up failed:', error);
            enqueueSnackbar('Sign up failed: ' + error.message, { variant: 'error' });
        }
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign Up
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="name"
                            label="Full Name"
                            name="name"
                            autoComplete="name"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="login"
                            label="Username"
                            name="login"
                            autoComplete="username"
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="new-password"
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="confirmPassword"
                            label="Confirm Password"
                            type="password"
                            id="confirmPassword"
                            autoComplete="new-password"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign Up
                        </Button>
                        <Grid container justifyContent="center">
                            <Grid item>
                                <Link component={RouterLink} to="/signIn" variant="body2">    
                                    {"Already have an account? Sign In"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}
