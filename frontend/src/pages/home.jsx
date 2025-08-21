export default function Home() {

  let token = localStorage.getItem("token")

  return (
    <div className="flex h-screen items-center justify-center">
      <h1 className="text-3xl font-bold text-gray-800">
        Página Principal
        {token}
      </h1>
    </div>
  );
}
