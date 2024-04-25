import axios from 'axios';

const API_URL = 'http://localhost:8081/service/paiement';

export const getAllPaiements = async () => {
    try {
        const response = await axios.get(`${API_URL}/all`);
        return response.data;
    } catch (error) {
        console.error("Error fetching all payments:", error);
        throw error;
    }
};

export const getPaiementsByClientId = async (idClient) => {
    try {
        const response = await axios.get(`${API_URL}/all/${idClient}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching payments for client ${idClient}:`, error);
        throw error;
    }
};

export const createPaiement = async (paiementDto) => {
    try {
        const response = await axios.post(`${API_URL}/`, paiementDto);
        return response.data;
    } catch (error) {
        console.error("Error creating payment:", error);
        throw error;
    }
};
