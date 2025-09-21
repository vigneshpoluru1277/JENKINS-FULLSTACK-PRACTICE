import { useState, useEffect } from "react";
import axios from "axios";
import config from "./config";

function BookManager() {
  const [books, setBooks] = useState([]);
  const [form, setForm] = useState({ title: "", author: "", isbn: "" });
  const [editingId, setEditingId] = useState(null);

  // Fetch all books
  const fetchBooks = async () => {
    try {
      const response = await axios.get(`${config.url}/books`);
      setBooks(response.data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  // Handle form input change
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Add or update book
  const addOrUpdateBook = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await axios.put(`${config.url}/books/${editingId}`, form);
        setEditingId(null);
      } else {
        await axios.post(`${config.url}/books`, form);
      }
      setForm({ title: "", author: "", isbn: "" });
      fetchBooks();
    } catch (error) {
      console.error("Error saving book:", error);
    }
  };

  // Delete book
  const deleteBook = async (id) => {
    try {
      await axios.delete(`${config.url}/books/${id}`);
      fetchBooks();
    } catch (error) {
      console.error("Error deleting book:", error);
    }
  };

  // Start editing
  const startEdit = (book) => {
    setForm({ title: book.title, author: book.author, isbn: book.isbn });
    setEditingId(book.id);
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        minHeight: "100vh",
        backgroundColor: "#f9f9f9",
      }}
    >
      <div
        style={{
          background: "white",
          padding: "30px",
          borderRadius: "10px",
          boxShadow: "0px 4px 12px rgba(0,0,0,0.1)",
          width: "80%",
          maxWidth: "800px",
          textAlign: "center",
        }}
      >
        <h1 style={{ marginBottom: "20px" }}>📚 Library Management System</h1>

        <form
          onSubmit={addOrUpdateBook}
          style={{ marginBottom: "20px", display: "flex", gap: "10px" }}
        >
          <input
            type="text"
            name="title"
            placeholder="Book Title"
            value={form.title}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="author"
            placeholder="Author"
            value={form.author}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="isbn"
            placeholder="ISBN"
            value={form.isbn}
            onChange={handleChange}
            required
          />
          <button type="submit">
            {editingId ? "Update Book" : "Add Book"}
          </button>
        </form>

        <h3>📖 All Books</h3>
        <table
          border="1"
          cellPadding="10"
          style={{ borderCollapse: "collapse", width: "100%" }}
        >
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Author</th>
              <th>ISBN</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.length === 0 ? (
              <tr>
                <td colSpan="5">No books available</td>
              </tr>
            ) : (
              books.map((b) => (
                <tr key={b.id}>
                  <td>{b.id}</td>
                  <td>{b.title}</td>
                  <td>{b.author}</td>
                  <td>{b.isbn}</td>
                  <td>
                    <button onClick={() => startEdit(b)}>✏ Edit</button>
                    <button onClick={() => deleteBook(b.id)}>❌ Delete</button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BookManager;
