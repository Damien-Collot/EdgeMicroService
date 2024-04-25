import axios from 'axios';

const API_URL = 'http://192.168.204.25:8081/service/product';

export const getProductById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching product by ID:", error);
        throw error;
    }
};

export const getProductByName = async (name) => {
    try {
        const response = await axios.get(`${API_URL}/name/${name}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching product by name: ${name}`, error);
        throw error;
    }
};

export const getAllProducts = async () => {
    try {
        const response = await axios.get(`${API_URL}/all`);
        return response.data;
    } catch (error) {
        console.error("Error fetching all products:", error);
        throw error;
    }
};
