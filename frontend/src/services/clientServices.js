import axios from 'axios';

const API_URL = 'http://localhost:8082/service/client';

export const getAllClients = async () => {
    try {
        const response = await axios.get(`${API_URL}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const getClient = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

export const signIn = async (login, password) => {
    try {
        const response = await axios.post(`${API_URL}/signin`, null, {
            params: { login, password }
        });
        return response;
    } catch (error) {
        throw error;
    }
};




export const signUp = async (name, login, password) => {
    try {
        const response = await axios.post(`${API_URL}/signup`, null, {
            params: { name, login, password }
        });
        return response;
    } catch (error) {
        throw error.response;
    }
};


export const deleteClient = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};
