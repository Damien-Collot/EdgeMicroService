import React from 'react';
import { useNavigate } from 'react-router-dom';
import { signIn } from '../services/clientServices';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import { Link as RouterLink } from 'react-router-dom';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useSnackbar } from 'notistack';

const defaultTheme = createTheme();

export default function SignIn() {
    let navigate = useNavigate();
    const { enqueueSnackbar } = useSnackbar();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const login = data.get('login');
        const password = data.get('password');
        const rememberMe = data.get('remember');

        try {
            const response = await signIn(login, password);
            console.log(response);
            if (response.status === 200) {
                enqueueSnackbar('Login successful!', { variant: 'success' });
                navigate("/home");
                if (rememberMe) {
                    localStorage.setItem('userId', response.data.id);
                    localStorage.setItem('userLogin', response.data.login);
                }
            } else {
                enqueueSnackbar('Login failed!', { variant: 'error' });
            }
        } catch (error) {
            console.error('Login failed:', error);
            enqueueSnackbar('Login failed: ' + error.message, { variant: 'error' });
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
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="login"
                            label="Username"
                            name="login"
                            autoComplete="username"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                        />
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary" name="remember" />}
                            label="Remember me"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign In
                        </Button>
                        <Grid container justifyContent="center">
                            <Grid item>
                                <Link component={RouterLink} to="/signUp" variant="body2">    
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}
