package com.studiopulsar.feintha.armortrims;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

@SuppressWarnings("unused")
public class alib {
    public static Vec3d VectorFromJson(JsonElement o) {
        double x = 0,y = 0,z = 0;
        if (o.isJsonArray()) {
            var a = o.getAsJsonArray();
            x = a.get(0).getAsDouble();
            y = a.get(1).getAsDouble();
            z = a.get(2).getAsDouble();
        } else if (o.isJsonObject()) {
            x = o.getAsJsonObject().get("x").getAsDouble();
            y = o.getAsJsonObject().get("y").getAsDouble();
            z = o.getAsJsonObject().get("z").getAsDouble();
        } else if (o.isJsonPrimitive() && o.getAsJsonPrimitive().isNumber()) {
            return longToVec3d(o.getAsLong());
        }
        return new Vec3d(x,y,z);
    }
    public static int packRGBf(Vec3f rgbVec) {
        int red = Math.round(rgbVec.getX() * 255.0f);
        int green = Math.round(rgbVec.getY() * 255.0f);
        int blue = Math.round(rgbVec.getZ() * 255.0f);
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));
        return (255 << 24) | (red << 16) | (green << 8) | blue;
    }

    public static Vec3i averageRGBi(ArrayList<Vec3i> colors) {
        if (colors.size() == 0) {
            throw new IllegalArgumentException("The list is empty.");
        }

        int sumR = 0, sumG = 0, sumB = 0;

        for (Vec3i color : colors) {
            sumR += color.getX();
            sumG += color.getY();
            sumB += color.getZ();
        }

        int avgR = sumR / colors.size();
        int avgG = sumG / colors.size();
        int avgB = sumB / colors.size();
        return new Vec3i(avgR, avgG, avgB);
    }

    public static Vec3f averageRGBf(ArrayList<Vec3f> colors) {
        if (colors.size() == 0) {
            throw new IllegalArgumentException("The list is empty.");
        }

        int sumR = 0, sumG = 0, sumB = 0;

        for (Vec3f color : colors) {
            sumR += color.getX();
            sumG += color.getY();
            sumB += color.getZ();
        }

        int avgR = sumR / colors.size();
        int avgG = sumG / colors.size();
        int avgB = sumB / colors.size();

        return new Vec3f(avgR, avgG, avgB);
    }
    public static int packRGBi(Vec3i rgbVec) {
        int red = Math.max(0, Math.min(255, rgbVec.getX()));
        int green = Math.max(0, Math.min(255, rgbVec.getY()));
        int blue = Math.max(0, Math.min(255, rgbVec.getZ()));
        return (255 << 24) | (red << 16) | (green << 8) | blue;
    }
    public static Vec3f unpackRGB(int packedRGB) {
        float red = ((packedRGB >> 16) & 0xFF) / 255.0f;
        float green = ((packedRGB >> 8) & 0xFF) / 255.0f;
        float blue = (packedRGB & 0xFF) / 255.0f;

        return new Vec3f(red, green, blue);
    }
    public static Vec3i unpackRGBi(int packedRGB) {
        float red = ((packedRGB >> 16) & 0xFF);
        float green = ((packedRGB >> 8) & 0xFF);
        float blue = (packedRGB & 0xFF);

        return new Vec3i(red, green, blue);
    }
    public static Vec3i unpackARGBi(int packedARGB) {
        int red = (packedARGB >> 16) & 0xFF;
        int green = (packedARGB >> 8) & 0xFF;
        int blue = packedARGB & 0xFF;

        return new Vec3i(red, green, blue);
    }

    public static Vec3f unpackARGBf(int packedARGB) {
        float red = ((packedARGB >> 16) & 0xFF) / 255.0f;
        float green = ((packedARGB >> 8) & 0xFF) / 255.0f;
        float blue = (packedARGB & 0xFF) / 255.0f;

        return new Vec3f(red, green, blue);
    }
    public static Vec3d longToVec3d(long value) {
        // Extract x, y, and z components from the long value
        double x = (double) (value & 0xFFFF);
        double y = (double) ((value >> 16) & 0xFFFF);
        double z = (double) ((value >> 32) & 0xFFFF);

        // Return the Vec3d with the extracted components
        return new Vec3d(x, y, z);
    }
    public static NbtCompound packBlockStateIntoCompound(BlockState s, NbtCompound c) {
        for (var prop : s.getProperties()) {
            String n = prop.getName();
            var value = prop.createValue(s);
            Object vL = value.value();
            if (vL instanceof Number num) {
                if (num instanceof Float f) {
                    c.putFloat(n, f);
                } else if (num instanceof Double d) {
                    c.putDouble(n,d);
                } else if (num instanceof Long l) {
                    c.putLong(n,l);
                } else if (num instanceof Integer i) {
                    c.putInt(n,i);
                } else if (num instanceof Byte b) {
                    c.putByte(n,b);
                } else if (num instanceof Short sH) {
                    c.putShort(n,sH);
                }
            } else if (vL instanceof Boolean bl) {
                c.putBoolean(n, bl);
            } else if (vL instanceof Identifier id) {
                c.putString(n,id.toString());
            }
        }
        return c;
    }

    // Checks if left is present and values equal in right.
    public static boolean checkNBTEquals(NbtCompound left, NbtCompound right) {
        if (left.getSize() == right.getSize() && left.getSize() == 0) {return true;}
        boolean bl = true;
        for (String key : left.getKeys()) {
            if (!bl || !right.contains(key)) {return false;}
            NbtElement e_L = left.get(key);
            NbtElement e_R = right.get(key);
            int elem_t = left.getType(key);
            if (right.getType(key) != elem_t) {
                return false;
            }
            if (left.get(key) instanceof NbtList lL && right.get(key) instanceof NbtList rL) {
                if (lL.size() != rL.size()) {return false;}
                int i = 0;
                for (NbtElement e : lL.subList(0, lL.size())) {
                    if (!e.asString().contentEquals(rL.get(i).asString())){
                        return false;
                    }
                    i++;
                }
            }

            if (e_L instanceof AbstractNbtNumber numL && e_R instanceof AbstractNbtNumber numR) {
                switch (elem_t) {
                    case NbtType.INT -> bl = numL.intValue() == numR.intValue();
                    case NbtType.SHORT -> bl = numL.shortValue() == numR.shortValue();
                    case NbtType.BYTE -> bl = numL.byteValue() == numR.byteValue();
                    case NbtType.FLOAT -> bl = numL.floatValue() == numR.floatValue();
                    case NbtType.DOUBLE -> bl = numL.doubleValue() == numR.doubleValue();
                    case NbtType.LONG -> bl = numL.longValue() == numR.longValue();
                }
            }
            switch (elem_t) {
                case NbtType.COMPOUND -> bl = checkNBTEquals(left.getCompound(key), right.getCompound(key));
                case NbtType.STRING -> bl = e_L.asString().contentEquals(e_R.asString());
            }
        }
        return bl;
    }

    public static NbtCompound json2NBT(JsonObject jsonObject) {
        NbtCompound nbtCompound = new NbtCompound();

        for (var entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement jsonElement = entry.getValue();

            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive primitive = jsonElement.getAsJsonPrimitive();
                if (primitive.isNumber()) {
                    if (primitive.getAsNumber() instanceof Integer i) {
                        nbtCompound.putInt(key, i);
                    } else if (primitive.getAsNumber() instanceof Float i) {
                        nbtCompound.putFloat(key, i);
                    } else if (primitive.getAsNumber() instanceof Double i) {
                        nbtCompound.putDouble(key, i);
                    } else if (primitive.getAsNumber() instanceof Short i) {
                        nbtCompound.putShort(key, i);
                    }
                } else if (primitive.isBoolean()) {
                    nbtCompound.putBoolean(key, primitive.getAsBoolean());
                } else if (primitive.isString()) {
                    nbtCompound.putString(key, primitive.getAsString());
                }
                // Add more conversions as needed for other primitive types
            } else if (jsonElement.isJsonObject() || jsonElement.isJsonArray()) {
                NbtCompound nestedCompound = json2NBT(jsonElement.getAsJsonObject());
                nbtCompound.put(key, nestedCompound);
            }
            // Add more conversions as needed for other types like arrays or nested objects
        }

        return nbtCompound;
    }
    public static <F,T> F getMixinField(T mixinType, String fieldName) {
        try {
            Field f = mixinType.getClass().getField(fieldName);
            f.setAccessible(true);
            //noinspection unchecked
            return (F) f.get(mixinType);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static <F,T> F setMixinField(T mixinType, String fieldName, F value) {
        try {
            Field f = mixinType.getClass().getField(fieldName);
            f.setAccessible(true);
            f.set(mixinType, value);
            //noinspection unchecked
            return (F)f.get(mixinType);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static <F,T> F setPrivateMixinField(T mixinType, String fieldName, F value) {
        try {
            Field f = mixinType.getClass().getField(fieldName);
            f.setAccessible(true);
            f.set(mixinType, value);
            //noinspection unchecked
            return (F)f.get(mixinType);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static <F,T> F getPrivateMixinField(T mixinType, String fieldName) {
        try {
            Field f = mixinType.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            //noinspection unchecked
            return (F) f.get(mixinType);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Comparable> T parseInputString(String input, String format) {
        assert format.startsWith("%");
        String result = String.format(input,format);
        return switch (format.charAt(format.length() - 1)) {
            case 'd' -> (T)(Object)Double.parseDouble(result);
            case 'f' -> (T)(Object)Float.parseFloat(result);
            case 'i' -> (T)(Object)Integer.parseInt(result);
            case 'b' -> (T)(Object)Boolean.parseBoolean(result);
            case 'o' -> (T)(Object)Integer.parseInt(result,3);
            // assume result is hex. Returns a string!!!
            case 'h' -> (T)result;
            default -> throw new IllegalStateException("Unexpected value: " + format.charAt(format.length() - 1));
        };
    }
    public static <T, R> R runMixinMethod(T mixinType, String methodName, Object ... args) {
        try {
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            Method f;
            if (args.length > 0) {
                f = mixinType.getClass().getMethod(methodName, argTypes);
            } else {
                f = mixinType.getClass().getMethod(methodName);
            }
            //noinspection unchecked
            return (R)f.invoke(mixinType, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T, R> R runConstructor(Class mixinType, Object ... args) {
        try {
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            Constructor f;
            if (args.length > 0) {
                f = mixinType.getConstructor(argTypes);
            } else {
                f = mixinType.getConstructor();
            }
            //noinspection unchecked
            return (R)f.newInstance(args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T, R> R runPrivateConstructor(Class mixinType, Object ... args) {
        try {
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
                System.out.println(argTypes[i]);
            }
            Constructor f;
            if (args.length > 0) {
                f = mixinType.getDeclaredConstructor(argTypes);
            } else {
                f = mixinType.getDeclaredConstructor();
            }
            f.setAccessible(true);
            return (R)f.newInstance(args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T, R> R runPrivateMixinMethod(T mixinType, String methodName, Object ... args) {
        try {
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            Method f;
            if (args.length > 0) {
                f = mixinType.getClass().getDeclaredMethod(methodName, argTypes);
            } else {
                f = mixinType.getClass().getDeclaredMethod(methodName);
            }
            f.setAccessible(true);
            //noinspection unchecked
            return (R)f.invoke(mixinType, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public static float getRandomFloat(Random random, float minValue, float maxValue) {
        return minValue + random.nextFloat() * (maxValue - minValue);
    }
    public static <T extends Entity> List<T> getEntitiesOfTypeInRange(World world, BlockPos pos, double range, EntityType<T> type) {
        Predicate<Entity> filter = entity -> entity.getType() == type && entity.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()) <= range * range;
        return world.getEntitiesByType(type, Box.of(Vec3d.of(pos),range, range, range), filter);
    }
    public static boolean isEntityNearBlock(Entity e, int radius, Block... blocks) {
        // Get the entity's position
        BlockPos entityPos = e.getBlockPos();

        // Check if any nearby block is a target block type
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = entityPos.add(x, y, z);
                    Block block = e.getWorld().getBlockState(pos).getBlock();
                    for (Block b : blocks) {
                        System.out.println("check");
                        if (block == b) {
                            return true;
                        }
                    }
                }
            }
        }

        // No nearby block is a target block type
        return false;
    }
    /**
     * Calculate a 64 bits hash by combining CRC32 with Adler32.
     *
     * @param bytes a byte array
     * @return a hash number
     */
    public static long getHash64(byte[] bytes) {

        CRC32 crc32 = new CRC32();
        Adler32 adl32 = new Adler32();

        crc32.update(bytes);
        adl32.update(bytes);

        long crc = crc32.getValue();
        long adl = adl32.getValue();
        return ((crc << 32) | adl) + crc << 8;
    }
    public static boolean stackCustomModelDataEquals(@NotNull ItemStack stack, int data) {
        if (!stack.getOrCreateNbt().contains("CustomModelData")) {return false;}
        return stack.getOrCreateNbt().getInt("CustomModelData") == data;
    }
    public static long getHash64(String s) {
        return getHash64(s.getBytes(StandardCharsets.UTF_8));
    }
    public static long bitenable(long var, long nbit) {
        return (var) |= (1L <<(nbit));
    }
    public static long bitdisable(long var, long nbit) {
        return (var) &= (1L <<(nbit));
    }
    public static long bitflip(long var, long nbit) {
        return (var) ^= (1L <<(nbit));
    }
    public static boolean getbit(long var, long nbit) {
        return ((var) & (1L <<(nbit))) == 1;
    }
    public static boolean getbitmask(long var, long mask) {
        return (var & mask) == mask;
    }
    public static long setbit(long var, long nbit, boolean value) {
        if (value) {
            return bitenable(var, nbit);
        } else {
            return bitdisable(var,nbit);
        }
    }
    public static BlockPos getBlockPosFromArray(long[] a) {
        if (a.length < 3) {return BlockPos.ORIGIN;}
        return new BlockPos((int)a[0], (int)a[1], (int)a[2]);
    }
    public static long[] getBlockPosAsArray(BlockPos d) {
        if (d == null) {return new long[]{0, 0, 0};}
        return new long[]{d.getX(), d.getY(), d.getZ()};
    }
    public static double[] getVec3dAsArray(Vec3d d) {
        if (d == null) {return new double[]{0d, 0d, 0d};}
        return new double[]{d.getX(), d.getY(), d.getZ()};
    }
    public static Class<?> getFunctionTemplateClass(Object object, int index) {
        Type genericInterface = object.getClass().getGenericInterfaces()[0];
        if (genericInterface instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (index >= 0 && index < typeArguments.length) {
                return (Class<?>) typeArguments[index];
            }
        }
        throw new IllegalArgumentException("Index out of bounds or template not found.");
    }
    public static Pair<Integer, Integer> XYPosFromOffset(int w, int offset) {
        assert w != 0;
        int x;
        int y;
        x = offset % w;    // % is the "modulo operator", the remainder of i / width;
        assert offset != 0;
        y = offset / w;    // where "/" is an integer division
        return Pair.of(x,y);
    }
    public static double lerp(double a, double b, float f) {
        return a + f * (b - a);
    }
    public static int mixRGB (int a, int b, float ratio) {
        if (ratio > 1f) {
            ratio = 1f;
        } else if (ratio < 0f) {
            ratio = 0f;
        }
        float iRatio = 1.0f - ratio;

        int aA = (a >> 24 & 0xff);
        int aR = ((a & 0xff0000) >> 16);
        int aG = ((a & 0xff00) >> 8);
        int aB = (a & 0xff);

        int bA = (b >> 24 & 0xff);
        int bR = ((b & 0xff0000) >> 16);
        int bG = ((b & 0xff00) >> 8);
        int bB = (b & 0xff);

        int A = (int)((aA * iRatio) + (bA * ratio));
        int R = (int)((aR * iRatio) + (bR * ratio));
        int G = (int)((aG * iRatio) + (bG * ratio));
        int B = (int)((aB * iRatio) + (bB * ratio));

        return A << 24 | R << 16 | G << 8 | B;
    }
}
