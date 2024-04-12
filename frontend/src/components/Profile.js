import React, { useState } from 'react';
import { Card, CardContent, Typography, Grid, Avatar, Box, IconButton, TextField } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import EditIcon from '@mui/icons-material/Edit';

export default function Profile() {
  const [userInfo, setUserInfo] = useState({
    name: "John Doe",
    email: "john.doe@example.com",
    bio: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque et euismod ligula. Morbi mattis pretium eros, non tristique erat volutpat non.",
  });
  const [editMode, setEditMode] = useState(false);

  const handleBioChange = (event) => {
    setUserInfo({ ...userInfo, bio: event.target.value });
  };

  return (
    <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center" minHeight="100vh">
    <Typography sx={{paddingBottom: "20px"}} variant="h4">Profil client</Typography>
      <Card sx={{ maxWidth: 600, width: '100%', m: 2 }}>
        <Box display="flex" justifyContent="center">
          <Avatar sx={{ bgcolor: "primary.main", width: 100, height: 100 }}>
            <AccountCircleIcon sx={{ fontSize: 80 }} />
          </Avatar>
        </Box>
        <CardContent>
          <Grid container spacing={2} alignItems="center" direction="column">
            <Grid item xs={12}>
              <Typography gutterBottom variant="h5" component="div" align="center">
                {userInfo.name}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary" align="center">
                {userInfo.email}
              </Typography>
            </Grid>
            <Grid item xs={12} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
              {editMode ? (
                <TextField
                  variant="outlined"
                  multiline
                  fullWidth
                  value={userInfo.bio}
                  onChange={handleBioChange}
                  onBlur={() => setEditMode(false)}
                  autoFocus
                />
              ) : (
                <>
                  <Typography variant="body2" color="text.secondary" align="center">
                    {userInfo.bio}
                  </Typography>
                  <IconButton onClick={() => setEditMode(true)} size="small" sx={{ ml: 1 }}>
                    <EditIcon fontSize="inherit" />
                  </IconButton>
                </>
              )}
            </Grid>
            {!editMode && (
              <Grid item xs={12}>
                <Typography variant="caption" display="block" gutterBottom>
                  Les modifications ne seront pas enregistrées.
                </Typography>
              </Grid>
            )}
          </Grid>
        </CardContent>
      </Card>
    </Box>
  );
}
