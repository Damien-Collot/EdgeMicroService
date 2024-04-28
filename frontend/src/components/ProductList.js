import React, { useState, useEffect } from 'react';
import { Box, Card, CardContent, Typography, Button, Grid, IconButton, Badge } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import RadioButtonUncheckedIcon from '@mui/icons-material/RadioButtonUnchecked';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import { green } from '@mui/material/colors';
import { getAllProducts } from '../services/productServices';

export default function ProductList() {
    const [products, setProducts] = useState([]);
    const [selectedProducts, setSelectedProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const productsData = await getAllProducts();
                setProducts(productsData.map(product => ({ ...product, quantity: 0 })));
            } catch (error) {
                console.error('Failed to fetch products:', error);
            }
        };

        fetchProducts();
    }, []);

    const handleSelectProduct = (product) => {
        const productIndex = selectedProducts.findIndex(p => p.id === product.id);
        if (productIndex < 0) {
            setSelectedProducts([...selectedProducts, { ...product, quantity: 1 }]);
        }
    };

    const updateProductQuantity = (productId, amount) => {
        const updatedProducts = selectedProducts.map(product =>
            product.id === productId ? { ...product, quantity: product.quantity + amount } : product
        ).filter(product => product.quantity > 0);

        setSelectedProducts(updatedProducts);
    };

    const calculateTotal = () => {
        return selectedProducts.reduce((total, product) => total + (product.montant * product.quantity), 0).toFixed(2);
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
                            <CardContent>
                                <Typography variant="h5">{product.name}</Typography>
                                <Typography color="text.secondary">{product.description}</Typography>
                                <Typography variant="body2">Prix: {product.montant} €</Typography>
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
