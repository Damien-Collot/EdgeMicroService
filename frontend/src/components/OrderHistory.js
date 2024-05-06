import React, { useState, useEffect } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography, Box, Collapse, IconButton, TableSortLabel } from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import { getOrdersByClient } from '../services/orderServices';

export default function OrderHistory() {
    const [orders, setOrders] = useState([]);
    const [order, setOrder] = useState('asc');
    const [orderBy, setOrderBy] = useState('label');
    useEffect(() => {
      const clientId = localStorage.getItem('userId');
      if (clientId) {
          const fetchOrders = async () => {
              try {
                  const ordersData = await getOrdersByClient(clientId);
                  setOrders(ordersData);
              } catch (error) {
                  console.error('Failed to fetch orders:', error);
              }
          };
  
          fetchOrders();
      } else {
          console.error('No client ID found in localStorage');
      }
  }, []);
  

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

    function Row({ order }) {
        const [open, setOpen] = useState(false);

        return (
            <>
                <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
                    <TableCell>{order.name}</TableCell>
                    <TableCell>{order.description}</TableCell>
                    <TableCell>{order.quantite}</TableCell>
                    <TableCell>{order.montant}</TableCell>
                    <TableCell>{order.date}</TableCell>
                    <TableCell>{order.total}</TableCell>
                </TableRow>
            </>
        );
    }
  
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Typography sx={{paddingBottom: "10px"}} variant="h4">Historique des commandes</Typography>
            <TableContainer component={Paper} sx={{ maxHeight: 600 }}>
                <Table stickyHeader aria-label="collapsible table">
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{ fontWeight: 'bold' }}>
                                <TableSortLabel
                                active={orderBy === 'name'}
                                direction={orderBy === 'name' ? order : 'asc'}
                                onClick={() => handleRequestSort('name')}
                                >
                                Name
                                </TableSortLabel>
                            </TableCell>
                            <TableCell sx={{ fontWeight: 'bold' }}>
                                <TableSortLabel
                                active={orderBy === 'description'}
                                direction={orderBy === 'description' ? order : 'asc'}
                                onClick={() => handleRequestSort('description')}
                                >
                                Description
                                </TableSortLabel>
                            </TableCell>
                            <TableCell sx={{ fontWeight: 'bold' }}>
                                <TableSortLabel
                                active={orderBy === 'quantite'}
                                direction={orderBy === 'quantite' ? order : 'asc'}
                                onClick={() => handleRequestSort('quantite')}
                                >
                                Quantite
                                </TableSortLabel>
                            </TableCell>
                            <TableCell sx={{ fontWeight: 'bold' }}>
                                <TableSortLabel
                                active={orderBy === 'montant'}
                                direction={orderBy === 'montant' ? order : 'asc'}
                                onClick={() => handleRequestSort('montant')}
                                >
                                Montant
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
                                active={orderBy === 'total'}
                                direction={orderBy === 'total' ? order : 'asc'}
                                onClick={() => handleRequestSort('total')}
                                >
                                Total
                                </TableSortLabel>
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sortOrders(orders).map((order) => (
                            <Row key={order.id} order={order}/>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
}
