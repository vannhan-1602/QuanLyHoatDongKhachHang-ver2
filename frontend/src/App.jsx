import React, { useState, useEffect } from "react";
import axios from "axios";

const BASE_URL = "http://localhost:8081/api";
const API_HOAT_DONG = `${BASE_URL}/hoat-dong`;
const API_LOOKUP = `${BASE_URL}/lookup`;

function App() {
  const [activities, setActivities] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filterType, setFilterType] = useState("");

  // State quản lý Modals
  const [showEditModal, setShowEditModal] = useState(false);
  const [viewingContent, setViewingContent] = useState(null); // Để xem nội dung chi tiết

  const [editingId, setEditingId] = useState(null);

  // State cho danh sách Lookup (chọn tên thay vì nhập ID)
  const [customers, setCustomers] = useState([]);
  const [leads, setLeads] = useState([]);
  const [staffs, setStaffs] = useState([]);

  const [form, setForm] = useState({
    khachHangId: "",
    leadId: "",
    loaiHoatDong: "Call",
    noiDung: "",
    nhanVienId: "",
    thoiGianThucHien: "",
  });

  useEffect(() => {
    fetchAll();
    fetchLookupData();
  }, []);

  const fetchAll = async () => {
    try {
      const res = await axios.get(API_HOAT_DONG);
      setActivities(res.data);
    } catch (err) {
      console.error("Lỗi tải dữ liệu:", err);
    }
  };

  const fetchLookupData = async () => {
    try {
      const [kh, ld, nv] = await Promise.all([
        axios.get(`${API_LOOKUP}/khach-hang`),
        axios.get(`${API_LOOKUP}/lead`),
        axios.get(`${API_LOOKUP}/nhan-vien`),
      ]);
      setCustomers(kh.data);
      setLeads(ld.data);
      setStaffs(nv.data);
    } catch (err) {
      console.error("Lỗi lookup:", err);
    }
  };

  const handleSave = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await axios.put(`${API_HOAT_DONG}/${editingId}`, form);
      } else {
        await axios.post(API_HOAT_DONG, form);
      }
      setShowEditModal(false);
      fetchAll();
    } catch (err) {
      alert("Lỗi khi lưu!");
    }
  };

  const openEditModal = (act = null) => {
    if (act) {
      setEditingId(act.id);
      const dateStr = act.thoiGianThucHien
        ? act.thoiGianThucHien.substring(0, 16)
        : "";
      setForm({ ...act, thoiGianThucHien: dateStr });
    } else {
      setEditingId(null);
      setForm({
        khachHangId: "",
        leadId: "",
        loaiHoatDong: "Call",
        noiDung: "",
        nhanVienId: "",
        thoiGianThucHien: "",
      });
    }
    setShowEditModal(true);
  };

  // Định dạng thời gian: Giờ:Phút AM/PM Ngày/Tháng/Năm
  const formatDateTime = (dateString) => {
    if (!dateString) return "N/A";
    const date = new Date(dateString);
    const time = date.toLocaleTimeString("en-US", {
      hour: "2-digit",
      minute: "2-digit",
      hour12: true,
    });
    const day = date.toLocaleDateString("vi-VN", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
    });
    return `${time} ${day}`;
  };

  const filtered = activities.filter(
    (a) =>
      (a.noiDung?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        a.tenKhachHang?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        a.tenLead?.toLowerCase().includes(searchTerm.toLowerCase())) &&
      (filterType === "" || a.loaiHoatDong === filterType),
  );

  return (
    <div className="min-h-screen bg-light py-5">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="fw-bold text-primary">Quản Lý Hoạt Động Khách Hàng</h2>
          <button
            className="btn btn-primary fw-bold shadow-sm"
            onClick={() => openEditModal()}
          >
            + GHI LẠI HOẠT ĐỘNG
          </button>
        </div>

        {/* Tìm kiếm & Lọc */}
        <div className="card border-0 shadow-sm mb-4">
          <div className="card-body row g-3">
            <div className="col-md-9">
              <input
                type="text"
                className="form-control bg-white border-0 py-2 shadow-none"
                placeholder="Tìm kiếm tên hoặc nội dung..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
            <div className="col-md-3">
              <select
                className="form-select border-0 bg-white shadow-none"
                value={filterType}
                onChange={(e) => setFilterType(e.target.value)}
              >
                <option value="">Tất cả loại hoạt động</option>
                <option value="Call"> Call</option>
                <option value="Meeting"> Meeting</option>
                <option value="Email"> Email</option>
                <option value="Zalo"> Zalo</option>
              </select>
            </div>
          </div>
        </div>

        {/* Bảng danh sách */}
        <div className="card border-0 shadow-sm overflow-hidden">
          <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-dark">
                <tr>
                  <th style={{ width: "10%" }} className="ps-4">
                    LOẠI
                  </th>
                  <th style={{ width: "20%" }}>ĐỐI TƯỢNG</th>
                  <th style={{ width: "35%" }}>NỘI DUNG</th>
                  <th style={{ width: "15%" }}>THỜI GIAN</th>
                  <th style={{ width: "10%" }}>NHÂN VIÊN</th>
                  <th style={{ width: "10%" }} className="text-center">
                    THAO TÁC
                  </th>
                </tr>
              </thead>
              <tbody>
                {filtered.map((a) => (
                  <tr key={a.id}>
                    <td className="ps-4">
                      <span
                        className={`badge rounded-pill p-2 px-3 ${getBadgeStyle(a.loaiHoatDong)}`}
                      >
                        {a.loaiHoatDong}
                      </span>
                    </td>
                    <td>
                      <div className="fw-bold text-dark">
                        {a.tenKhachHang || a.tenLead}
                      </div>
                      <small
                        className="text-muted text-uppercase"
                        style={{ fontSize: "0.7rem" }}
                      >
                        {a.khachHangId ? "Khách hàng" : "Lead"}
                      </small>
                    </td>
                    <td>
                      {/* Hiển thị giới hạn 2 dòng, nhấn để xem hết */}
                      <div
                        className="text-secondary small cursor-pointer"
                        style={{
                          display: "-webkit-box",
                          WebkitLineClamp: 2,
                          WebkitBoxOrient: "vertical",
                          overflow: "hidden",
                          cursor: "pointer",
                        }}
                        onClick={() => setViewingContent(a.noiDung)}
                        title="Nhấn để xem chi tiết"
                      >
                        {a.noiDung}
                      </div>
                    </td>
                    <td className="text-muted small fw-bold">
                      {formatDateTime(a.thoiGianThucHien)}
                    </td>
                    <td>
                      <span className="badge bg-primary-subtle text-primary border border-primary-subtle">
                        {a.tenNhanVien}
                      </span>
                    </td>
                    <td className="text-center">
                      <button
                        className="btn btn-sm text-warning fw-bold"
                        onClick={() => openEditModal(a)}
                      >
                        Sửa
                      </button>
                      <button
                        className="btn btn-sm text-danger fw-bold ms-2"
                        onClick={() => {
                          if (window.confirm("Xóa hoạt động này?"))
                            axios
                              .delete(`${API_HOAT_DONG}/${a.id}`)
                              .then(fetchAll);
                        }}
                      >
                        Xóa
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {/* MODAL XEM CHI TIẾT NỘI DUNG (GIÚP ĐỌC HẾT DỮ LIỆU) */}
      {viewingContent && (
        <div
          className="modal show d-block"
          style={{
            backgroundColor: "rgba(0,0,0,0.6)",
            backdropFilter: "blur(3px)",
          }}
        >
          <div className="modal-dialog modal-dialog-centered">
            <div className="modal-content border-0 shadow-lg rounded-4">
              <div className="modal-header bg-light border-0 p-4">
                <h5 className="modal-title fw-bold text-primary">
                  Chi tiết nội dung
                </h5>
                <button
                  type="button"
                  className="btn-close"
                  onClick={() => setViewingContent(null)}
                ></button>
              </div>
              <div
                className="modal-body p-4"
                style={{
                  whiteSpace: "pre-wrap",
                  maxHeight: "400px",
                  overflowY: "auto",
                }}
              >
                {viewingContent}
              </div>
              <div className="modal-footer border-0 p-4 pt-0">
                <button
                  type="button"
                  className="btn btn-secondary px-4 fw-bold"
                  onClick={() => setViewingContent(null)}
                >
                  Đóng
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* MODAL THÊM / SỬA */}
      {showEditModal && (
        <div
          className="modal show d-block"
          style={{ backgroundColor: "rgba(0,0,0,0.5)" }}
        >
          <div className="modal-dialog modal-dialog-centered">
            <div className="modal-content border-0 shadow-lg rounded-4">
              <div className="modal-header bg-primary text-white border-0 p-4">
                <h5 className="modal-title fw-bold">
                  {editingId ? "Cập Nhật Hoạt Động" : "Ghi Hoạt Động Mới"}
                </h5>
                <button
                  type="button"
                  className="btn-close btn-close-white"
                  onClick={() => setShowEditModal(false)}
                ></button>
              </div>
              <form onSubmit={handleSave}>
                <div className="modal-body p-4">
                  <div className="mb-3">
                    <label className="form-label small fw-bold">
                      CHỌN KHÁCH HÀNG
                    </label>
                    <select
                      className="form-select bg-light border-0"
                      value={form.khachHangId || ""}
                      onChange={(e) =>
                        setForm({
                          ...form,
                          khachHangId: e.target.value,
                          leadId: "",
                        })
                      }
                    >
                      <option value="">-- Chọn khách hàng --</option>
                      {customers.map((c) => (
                        <option key={c.id} value={c.id}>
                          {c.tenKhachHang}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="mb-3">
                    <label className="form-label small fw-bold">
                      HOẶC CHỌN LEAD
                    </label>
                    <select
                      className="form-select bg-light border-0"
                      value={form.leadId || ""}
                      onChange={(e) =>
                        setForm({
                          ...form,
                          leadId: e.target.value,
                          khachHangId: "",
                        })
                      }
                    >
                      <option value="">-- Chọn lead --</option>
                      {leads.map((l) => (
                        <option key={l.id} value={l.id}>
                          {l.tenLead}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="row mb-3">
                    <div className="col-md-6">
                      <label className="form-label small fw-bold">
                        LOẠI HOẠT ĐỘNG
                      </label>
                      <select
                        className="form-select bg-light border-0"
                        value={form.loaiHoatDong}
                        onChange={(e) =>
                          setForm({ ...form, loaiHoatDong: e.target.value })
                        }
                      >
                        <option value="Call">Call</option>
                        <option value="Meeting">Meeting</option>
                        <option value="Email">Email</option>
                        <option value="Zalo">Zalo</option>
                      </select>
                    </div>
                    <div className="col-md-6">
                      <label className="form-label small fw-bold">
                        THỜI GIAN
                      </label>
                      <input
                        type="datetime-local"
                        className="form-control bg-light border-0"
                        value={form.thoiGianThucHien}
                        onChange={(e) =>
                          setForm({ ...form, thoiGianThucHien: e.target.value })
                        }
                      />
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label small fw-bold">
                      NỘI DUNG TRAO ĐỔI
                    </label>
                    <textarea
                      className="form-control bg-light border-0"
                      rows="4"
                      value={form.noiDung}
                      onChange={(e) =>
                        setForm({ ...form, noiDung: e.target.value })
                      }
                      required
                      placeholder="Nhập tóm tắt cuộc hội thoại..."
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label small fw-bold">
                      NHÂN VIÊN PHỤ TRÁCH
                    </label>
                    <select
                      className="form-select bg-light border-0"
                      value={form.nhanVienId}
                      onChange={(e) =>
                        setForm({ ...form, nhanVienId: e.target.value })
                      }
                      required
                    >
                      <option value="">-- Chọn nhân viên --</option>
                      {staffs.map((s) => (
                        <option key={s.id} value={s.id}>
                          {s.name}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
                <div className="modal-footer border-0 p-4 pt-0">
                  <button
                    type="button"
                    className="btn btn-light px-4 fw-bold text-muted"
                    onClick={() => setShowEditModal(false)}
                  >
                    Đóng
                  </button>
                  <button
                    type="submit"
                    className="btn btn-primary px-5 fw-bold shadow"
                  >
                    LƯU DỮ LIỆU
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

const getBadgeStyle = (type) => {
  const styles = {
    Meeting: "bg-success-subtle text-success border border-success",
    Call: "bg-info-subtle text-info border border-info",
    Email: "bg-warning-subtle text-warning border border-warning",
    Zalo: "bg-primary-subtle text-primary border border-primary",
  };
  return styles[type] || "bg-secondary-subtle text-secondary";
};

export default App;
