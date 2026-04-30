import axios from 'axios'

export const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

// Add a request interceptor to add the auth token
apiClient.interceptors.request.use(
  (config) => {
    // You can add logic here to get the token from Clerk or localStorage
    // const token = localStorage.getItem('token')
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`
    // }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Add a response interceptor to handle common errors
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    const message = error.response?.data?.message || error.message
    console.error('API Error:', message)
    
    // For enterprise apps, you might want to handle 401 (Unauthorized) globally
    if (error.response?.status === 401) {
      // Redirect to login or refresh token
    }
    
    return Promise.reject(error)
  }
)
