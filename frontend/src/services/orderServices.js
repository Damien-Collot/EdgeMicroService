import axios from 'axios';

const API_URL = 'http://localhost:8083/service/order';

export const createOrder = async (orderData) => {
    try {
        const response = await axios.post(`${API_URL}/addProductToOrder`, orderData);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getOrder = async (idCommande) => {
    try {
        const response = await axios.get(`${API_URL}/getCommande${idCommande}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getOrdersByClient = async (idClient) => {
    if (!idClient) {
        console.error('Client ID is null or undefined');
        return Promise.reject('Client ID is null or undefined');
    }

    try {
        const response = await axios.get(`${API_URL}/getCommandes/${idClient}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

