import React, { useState } from 'react';
import { Box, Card, CardContent, Typography, Button, Grid, IconButton, Badge } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import RadioButtonUncheckedIcon from '@mui/icons-material/RadioButtonUnchecked';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import { green } from '@mui/material/colors';

const products = [
    { id: 1, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 2, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 3, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
    { id: 4, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 5, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 6, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
    { id: 7, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 8, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 9, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
    { id: 10, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 11, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 12, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
    { id: 13, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 14, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 15, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
    { id: 16, name: 'Product 1', description: 'Description of product 1', price: 10.00 },
    { id: 17, name: 'Product 2', description: 'Description of product 2', price: 15.00 },
    { id: 18, name: 'Product 3', description: 'Description of product 3', price: 20.00 },
  ].map(product => ({ ...product, quantity: 1 }));

  export default function ProductList() {
    const [selectedProducts, setSelectedProducts] = useState([]);
  
    const handleSelectProduct = (product) => {
      const isSelected = selectedProducts.find(p => p.id === product.id);
      if (!isSelected) {
        setSelectedProducts([...selectedProducts, product]);
      }
    };
  
    const updateProductQuantity = (productId, amount) => {
      const updatedProducts = selectedProducts.map(product =>
        product.id === productId ? { ...product, quantity: product.quantity + amount } : product
      ).filter(product => product.quantity > 0);
  
      setSelectedProducts(updatedProducts);
    };
  
    const calculateTotal = () => {
      return selectedProducts.reduce((total, product) => total + (product.price * product.quantity), 0).toFixed(2);
    };
  
    return (
      <Box sx={{ flexGrow: 1 }}>
        <Box sx={{ position: 'sticky', top: 0, bgcolor: 'background.paper', zIndex: 1, p: 2, display: 'flex', justifyContent: 'space-between' }}>
          <Typography variant="h4">Liste des Produits</Typography>
          <Badge badgeContent={selectedProducts.reduce((total, product) => total + product.quantity, 0)} color="primary">
            <ShoppingCartIcon />
          </Badge>
        </Box>
        <Grid container spacing={3}>
          {products.map(product => (
            <Grid item xs={12} sm={6} md={4} key={product.id}>
              <Card 
                sx={{ 
                    position: 'relative', 
                    '&:hover': {
                        backgroundColor: 'action.hover',
                        cursor: 'pointer',
                        boxShadow: 3,
                    }
                }}
                onClick={() => handleSelectProduct(product)}
                >
                <CardContent onClick={() => handleSelectProduct(product)}>
                  <Typography variant="h5">{product.name}</Typography>
                  <Typography color="text.secondary">{product.description}</Typography>
                  <Typography variant="body2">Prix: {product.price} €</Typography>
                  {selectedProducts.find(p => p.id === product.id) && (
                    <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'flex-end', pt: 2 }}>
                      <IconButton size="small" onClick={(e) => { e.stopPropagation(); updateProductQuantity(product.id, -1); }}>
                        <RemoveIcon />
                      </IconButton>
                      <Typography sx={{ mx: 1 }}>{selectedProducts.find(p => p.id === product.id).quantity}</Typography>
                      <IconButton size="small" onClick={(e) => { e.stopPropagation(); updateProductQuantity(product.id, 1); }}>
                        <AddIcon />
                      </IconButton>
                    </Box>
                  )}
                </CardContent>
                <Box sx={{ position: 'absolute', top: 8, right: 8 }}>
                  {selectedProducts.find(p => p.id === product.id) ? <CheckCircleIcon sx={{ color: green[500] }} /> : <RadioButtonUncheckedIcon />}
                </Box>
              </Card>
            </Grid>
          ))}
        </Grid>
        <Box sx={{ position: 'sticky', bottom: 0, bgcolor: 'background.paper', borderTop: 1, borderColor: 'divider', display: 'flex', justifyContent: 'space-between', p: 2, zIndex: 1 }}>
          <Typography variant="h6">Total: {calculateTotal()} €</Typography>
          <Button variant="contained">Passer la commande</Button>
        </Box>
      </Box>
    );
  }