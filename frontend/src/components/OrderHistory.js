import React, { useState } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography, Box, Collapse, IconButton, TableSortLabel } from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

const orders = [
    { 
      id: 1, 
      label: 'Commande 001', 
      date: '2023-04-12', 
      amount: '120.00', 
      products: [
        { name: 'Produit A', qty: 2, description: 'Description de Produit A' }, 
        { name: 'Produit B', qty: 1, description: 'Description de Produit B' }
      ] 
    },
    { 
      id: 2, 
      label: 'Commande 002', 
      date: '2023-04-15', 
      amount: '80.00', 
      products: [
        { name: 'Produit C', qty: 1, description: 'Description de Produit C' }, 
        { name: 'Produit D', qty: 3, description: 'Description de Produit D' }
      ] 
    },
    // Plus de commandes si nécessaire...
  ];
  

function Row({ order }) {
  const [open, setOpen] = useState(false);

  return (
    <>
      <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
        <TableCell>
          <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">{order.label}</TableCell>
        <TableCell>{order.date}</TableCell>
        <TableCell>{order.amount}</TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Typography variant="h6" gutterBottom component="div">
                Produits de la commande
              </Typography>
              <Table size="small" aria-label="products">
                <TableHead>
                  <TableRow>
                    <TableCell style={{ fontWeight: 'bold' }}>Nom du Produit</TableCell>
                    <TableCell style={{ fontWeight: 'bold' }}>Description</TableCell>
                    <TableCell align="right" style={{ fontWeight: 'bold' }}>Quantité</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {order.products.map((productRow) => (
                    <TableRow key={productRow.name}>
                      <TableCell component="th" scope="row">{productRow.name}</TableCell>
                      <TableCell>{productRow.description}</TableCell>
                      <TableCell align="right">{productRow.qty}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </>
  );
}

export default function OrderHistory() {
    const [open, setOpen] = useState(false);
    const [order, setOrder] = useState('asc');
    const [orderBy, setOrderBy] = useState('label');
  
    const handleRequestSort = (property) => {
      const isAsc = orderBy === property && order === 'asc';
      setOrder(isAsc ? 'desc' : 'asc');
      setOrderBy(property);
    };
  
    const sortOrders = (array) => {
      return array.sort((a, b) => {
        if (order === 'asc') {
          return a[orderBy] < b[orderBy] ? -1 : 1;
        } else {
          return a[orderBy] > b[orderBy] ? -1 : 1;
        }
      });
    };
  
    return (
        <Box sx={{ flexGrow: 1 }}>
          <Typography sx={{paddingBottom: "10px"}} variant="h4">Historique des commandes</Typography>
        <TableContainer component={Paper} sx={{ maxHeight: 600 }}>
            <Table stickyHeader aria-label="collapsible table">
            <TableHead>
                <TableRow>
                <TableCell />
                <TableCell sx={{ fontWeight: 'bold' }}>
                    <TableSortLabel
                    active={orderBy === 'label'}
                    direction={orderBy === 'label' ? order : 'asc'}
                    onClick={() => handleRequestSort('label')}
                    >
                    Libellé
                    </TableSortLabel>
                </TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>
                    <TableSortLabel
                    active={orderBy === 'date'}
                    direction={orderBy === 'date' ? order : 'asc'}
                    onClick={() => handleRequestSort('date')}
                    >
                    Date
                    </TableSortLabel>
                </TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>
                    <TableSortLabel
                    active={orderBy === 'amount'}
                    direction={orderBy === 'amount' ? order : 'asc'}
                    onClick={() => handleRequestSort('amount')}
                    >
                    Montant
                    </TableSortLabel>
                </TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {sortOrders(orders).map((order) => (
                <Row key={order.id} order={order} setOpen={setOpen} open={open}/>
                ))}
            </TableBody>
            </Table>
        </TableContainer>
      </Box>
    );
  }